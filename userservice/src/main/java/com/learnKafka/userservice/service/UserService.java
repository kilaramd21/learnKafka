package com.learnKafka.userservice.service;

import com.learnKafka.userservice.dto.CreateUserRequestDto;
import com.learnKafka.userservice.entity.User;
import com.learnKafka.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void createUser(CreateUserRequestDto createUserRequestDto) {

        User user = modelMapper.map(createUserRequestDto, User.class);
        User savedUser = userRepository.save(user);
    }

}
