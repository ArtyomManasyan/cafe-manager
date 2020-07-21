package com.cafeManagerAssignment.cafeManager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private boolean isManager;
}
