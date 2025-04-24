package com.kickproject.user.services;

import com.kickproject.user.dto.UserDto;

public interface UserService {
    UserDto getUser(String userPhone, Long userId);
    UserDto saveUser(UserDto userDto);
    void deleteUser(String userPhone, Long userId);
    UserDto editUser(String userPhone, Long userId, UserDto request);

}
