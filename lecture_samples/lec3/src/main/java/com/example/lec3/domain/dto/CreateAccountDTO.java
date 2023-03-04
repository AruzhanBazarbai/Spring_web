package com.example.lec3.domain.dto;

import lombok.Data;

@Data
public class CreateAccountDTO {
    private String username;

    private String password;

    private String email;

}
