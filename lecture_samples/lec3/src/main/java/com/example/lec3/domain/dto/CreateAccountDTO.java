package com.example.lec3.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class CreateAccountDTO {
    private String username;

    private String password;
    @Schema(description = "valid email address", example = "john_wick@mail.ru")
    private String email;

    private UUID uuidOfRequest;
    @Schema(description = "loginDate", type = "string", example = "2023-04-01T05:37:26.123+0800")
    private ZonedDateTime loginDate;


}
