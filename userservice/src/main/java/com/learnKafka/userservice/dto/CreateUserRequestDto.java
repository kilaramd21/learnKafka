package com.learnKafka.userservice.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {

    private Long id;
    private String fullName;
    private String email;
}
