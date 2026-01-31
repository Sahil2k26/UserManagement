package com.usermanagement.usermanagement.services.implementations;

import com.usermanagement.usermanagement.dto.CreateUserDto;
import com.usermanagement.usermanagement.dto.UserDto;
import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepository;
import com.usermanagement.usermanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository; // an argument becomes required only when it is declared final
    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = users
                .stream()
                .map(user -> new UserDto(user.getId(),user.getPhoneNo(), user.getPassword())).
                toList();

        return userDtoList;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user= userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User not found"));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto createNewUser(CreateUserDto req) {
        User newUser=modelMapper.map(req,User.class);
        User user= userRepository.save(newUser);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("User with id "+id+" doesnt exists");
        }
        userRepository.deleteById(id);

    }

    @Override
    public UserDto updateUserById(Long id, CreateUserDto req) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("User doesnt exists "));

        modelMapper.map(req,user);

        user = userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updatePartialUserById(Long id, Map<String, Object> updates) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("User doesnt exists "));
        updates.forEach((field,value)->{
            switch (field) {
                case "password":
                    user.setPassword((String) value);
                    break;
                case "phoneNo" :
                    user.setPhoneNo((String) value);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + field);
            }
        });
        // user must be final in switch statement

        User newUser = userRepository.save(user);

        return modelMapper.map(newUser,UserDto.class);

    }
}
