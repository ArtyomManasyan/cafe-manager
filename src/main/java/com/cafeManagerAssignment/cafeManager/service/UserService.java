package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.SignInResponseDto;
import com.cafeManagerAssignment.cafeManager.dto.SignUpResponseDto;
import com.cafeManagerAssignment.cafeManager.dto.UserDto;
import org.springframework.web.server.ResponseStatusException;


public interface UserService {

    /**
     * Create user (ROLE waiter) whit given user information.
     *
     * @param userDto is a new waiter which will creating
     *             after checking if it is not exist in database.
     * @throws ResponseStatusException if user already exist in database.
     * @return user which is stored in database.
     */
    SignUpResponseDto createWaiter(UserDto userDto);

    /**
     * Checking is user exist in database.
     *
     * @param username an uniq data identifying user.
     * @param password an data which encrypted version stored in database.
     * @throws ResponseStatusException if no user exist in database with given username and password.
     * @return
     */
    SignInResponseDto signIn(String username, String password);
}
