package com.cafeManagerAssignment.cafeManager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class SignUpResponseDto {

    private String firstName;

    private String lastName;

    private String username;
}
