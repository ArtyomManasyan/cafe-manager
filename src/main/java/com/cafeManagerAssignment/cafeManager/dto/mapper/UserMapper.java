package com.cafeManagerAssignment.cafeManager.dto.mapper;

import com.cafeManagerAssignment.cafeManager.dto.SignUpResponseDto;
import com.cafeManagerAssignment.cafeManager.dto.UserDto;
import com.cafeManagerAssignment.cafeManager.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UserMapper {

    public static User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        Optional<UserDto> optional = Optional.of(userDto);
        return new User()
                .setId(optional.map(UserDto::getId).orElse(null))
                .setManager(optional.map(UserDto::isManager).orElse(false))
                .setFirstName(optional.map(UserDto::getFirstName).orElse(null))
                .setLastName(optional.map(UserDto::getLastName).orElse(null))
                .setUsername(optional.map(UserDto::getUsername).orElse(null))
                .setPassword(optional.map(UserDto::getUsername).orElse(null));
    }

    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        Optional<User> optional = Optional.of(user);
        return new UserDto()
                .setId(optional.map(User::getId).orElse(null))
                .setManager(optional.map(User::isManager).orElse(false))
                .setFirstName(optional.map(User::getFirstName).orElse(null))
                .setLastName(optional.map(User::getLastName).orElse(null))
                .setUsername(optional.map(User::getUsername).orElse(null))
                .setPassword(optional.map(User::getPassword).orElse(null));
    }

    public static SignUpResponseDto toSignUpResponseDto(User user) {
        Optional<User> optional = Optional.ofNullable(user);
        return new SignUpResponseDto()
                .setFirstName(optional.map(User::getFirstName).orElse(null))
                .setLastName(optional.map(User::getLastName).orElse(null))
                .setUsername(optional.map(User::getUsername).orElse(null));
    }
}
