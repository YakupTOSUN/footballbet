package com.bet.sportoto.account.service;

import com.bet.sportoto.account.controller.request.LoginRequest;
import com.bet.sportoto.account.controller.request.RegisterRequest;
import com.bet.sportoto.exception.ForbiddenMailException;
import com.bet.sportoto.exception.GeneralException;
import com.bet.sportoto.exception.UserMailExistsException;
import com.bet.sportoto.mail.MailService;
import com.bet.sportoto.mapper.ModelMapperManager;
import com.bet.sportoto.security.SecurityContextUtil;
import com.bet.sportoto.security.TokenManager;
import com.bet.sportoto.user.entity.User;
import com.bet.sportoto.user.entity.UserDto;
import com.bet.sportoto.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {
    private final UserRepository userRepository;
    private final TokenManager tokenManager;
    private final ModelMapperManager modelMapperManager;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextUtil securityContextUtil;
    private final MailService mailService;

    @Override
    public UserDto register(RegisterRequest request) {

        boolean requestedUserNameisExist = this.userRepository.existsByMail(request.getMail());
        User user = new User();
        if (requestedUserNameisExist) {
            throw new UserMailExistsException("This mailing address already exists");
        }
        int a = request.getMail().indexOf("@");
        int b = request.getMail().indexOf(".com");
        if(a==-1 || b==-1){
            throw new GeneralException("Email address is invalid");
        }
        user = user.create(request);
                userRepository.save(user.create(request));
        return modelMapperManager.forResponse().map(user, UserDto.class);
    }

    @Override
    public ResponseEntity<String> login(LoginRequest request) {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(),
                        request.getPassword()));
        return ResponseEntity.ok(tokenManager.generateToken(request.getMail()));
    }

    @Override
    public UserDto getMe() {
        User user = securityContextUtil.getCurrentUser();
        return modelMapperManager.forResponse().map(user, UserDto.class);
    }

    @Override
    public String putPassword(String mail) {
        User user =  userRepository.findByMail(mail).orElseThrow(
                ()-> new ForbiddenMailException(mail + " This mailing address does not exist")
        );
        Random randomPassword = new Random();
        String newPassword = randomPassword.nextDouble(10)+"";
        newPassword = newPassword.replace(".","1");
        user.setPassword(newPassword);
        user.setUpdateDt(LocalDateTime.now());
        userRepository.save(user);
        return mailService.sendMailPassword(user.getPassword(),user.getMail());
    }


}
