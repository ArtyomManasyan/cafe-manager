package com.cafeManagerAssignment.cafeManager.controller.v1.util;

import com.cafeManagerAssignment.cafeManager.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthUtil {

    public static Long getUserId() {
        UserPrincipal userPrincipal = getUserPrincipal();

        return userPrincipal.getId();
    }

    public static boolean isManager() {
        UserPrincipal userPrincipal = getUserPrincipal();

        return userPrincipal.isAdmin();
    }

    private static UserPrincipal getUserPrincipal() {
        return (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
