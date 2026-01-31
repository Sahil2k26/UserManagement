package com.usermanagement.usermanagement.services;

import com.usermanagement.usermanagement.dto.CreateUserDto;
import com.usermanagement.usermanagement.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
        List<UserDto> getAllUsers();
        UserDto getUserById(Long id);
        UserDto createNewUser(CreateUserDto req);

    void deleteUserById(Long id);

    UserDto updateUserById(Long id, CreateUserDto req);

    UserDto updatePartialUserById(Long id, Map<String, Object> updates);
}

