package com.bet.sportoto.user.service;

import com.bet.sportoto.exception.NotFoundUserException;
import com.bet.sportoto.user.entity.User;
import com.bet.sportoto.user.entity.UserDto;
import com.bet.sportoto.user.entity.UserRole;
import com.bet.sportoto.user.repository.UserRepository;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void changedPassword(String userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(userId + " No user with ID found"));
        user.setPassword(newPassword);
        user.setUpdateDt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void changedRole(String userId, UserRole userRole) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(userId + " No user with ID found"));
        user.setUserRole(userRole);
        userRepository.save(user);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Pageable allPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort().isEmpty() ? Sort.by("createDt").descending() : pageable.getSort());
        Page<User> pageUser = userRepository.findAll(allPageable);
        return pageUser.map((user) -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(userId + " user not found!"));
    }

    @Override
    public void addUserBetRound(User user, UserBetRound userBetRound) {
        user.addUserBetRound(userBetRound);
        userRepository.save(user);
    }
}
