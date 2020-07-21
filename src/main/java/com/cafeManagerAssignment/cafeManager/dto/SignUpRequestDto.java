package com.cafeManagerAssignment.cafeManager.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private boolean isManager;
}
