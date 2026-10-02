package com.learnKafka.userservice.controller;

import com.learnKafka.userservice.dto.CreateUserRequestDto;
import com.learnKafka.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_USER_TOPIC;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto);
        return ResponseEntity.ok("User is created");
    }

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC, ""+i%2, message+i);
        }
        return ResponseEntity.ok("Message queued");
    }

}
