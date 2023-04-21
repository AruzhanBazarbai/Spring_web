package com.example.elkex.domain.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class CreateAccountDTO {
    private String username;

    private String password;
    private String email;

    private UUID uuidOfRequest;
    private ZonedDateTime loginDate;


}
