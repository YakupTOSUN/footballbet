package com.bet.sportoto.security;

import com.bet.sportoto.user.entity.User;
import com.bet.sportoto.user.entity.UserRole;
import com.bet.sportoto.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsManager implements UserDetailsService {

    private  final UserRepository userRepository;

    @PostConstruct
    public void init(){
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            User admin = new User();
            admin.setMail("ADMIN");
            admin.setUserRole(UserRole.ROLE_ADMIN);
            admin.setPassword("12345");
            userRepository.save(admin);
            }
        }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByMail(mail);

        User user = optionalUser.orElseThrow(() -> new EntityNotFoundException("Not found user."));

        return new UserCustomDetails(user);
    }
}
