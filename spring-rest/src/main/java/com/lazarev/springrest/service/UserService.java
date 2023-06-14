package com.lazarev.springrest.service;

import com.lazarev.springrest.dto.UserDto;
import com.lazarev.springrest.entity.User;
import com.lazarev.springrest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUserById(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("User not found"));
        return new UserDto(user.getUsername(), user.getEmail());
    }
}
