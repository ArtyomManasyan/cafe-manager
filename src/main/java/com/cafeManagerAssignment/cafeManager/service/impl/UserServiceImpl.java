package com.cafeManagerAssignment.cafeManager.service.impl;

import com.cafeManagerAssignment.cafeManager.dto.SignInResponseDto;
import com.cafeManagerAssignment.cafeManager.dto.SignUpResponseDto;
import com.cafeManagerAssignment.cafeManager.dto.UserDto;
import com.cafeManagerAssignment.cafeManager.dto.mapper.UserMapper;
import com.cafeManagerAssignment.cafeManager.model.User;
import com.cafeManagerAssignment.cafeManager.repository.UserRepository;
import com.cafeManagerAssignment.cafeManager.service.TokenService;
import com.cafeManagerAssignment.cafeManager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignUpResponseDto createWaiter(UserDto userDto) {
        userRepository.findByUsername(userDto.getUsername()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with username = " + userDto.getFirstName() + "already exist");
        });

        userDto.setManager(false);
        User user = UserMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return UserMapper.toSignUpResponseDto(userRepository.save(user));
    }

    @Override
    public SignInResponseDto signIn(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user with username = " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong password or username");
        }

        return new SignInResponseDto()
                .setType("Bearer")
                .setToken(tokenService.generateToken(user));

    }
}
