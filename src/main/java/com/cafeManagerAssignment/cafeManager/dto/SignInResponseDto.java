package com.cafeManagerAssignment.cafeManager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class SignInResponseDto {

    private String token;
    private String type;
}
