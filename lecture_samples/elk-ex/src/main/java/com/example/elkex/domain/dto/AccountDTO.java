package com.example.elkex.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountDTO {
    private String username;

    private String password;

    private String email;

    private ZonedDateTime loginDate;

}