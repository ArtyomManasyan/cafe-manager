package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.model.User;
import com.cafeManagerAssignment.cafeManager.model.UserPrincipal;

public interface TokenService {
    String generateToken(User user);

    UserPrincipal parseToken(String token);
}