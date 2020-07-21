package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.SignInResponseDto;
import com.cafeManagerAssignment.cafeManager.dto.SignUpResponseDto;
import com.cafeManagerAssignment.cafeManager.dto.UserDto;


public interface UserService {

    /**
     * Create user (ROLE waiter) whit given user information.
     *
     * @param user
     * @return
     */
    SignUpResponseDto createWaiter(UserDto user);

    SignInResponseDto signIn(String username, String password);
}
