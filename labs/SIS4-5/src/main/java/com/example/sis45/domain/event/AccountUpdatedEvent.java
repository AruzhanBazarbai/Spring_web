package com.example.sis45.domain.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountUpdatedEvent{
    String agregateObjectType;
    String agregateObjectId;
    String messagePayload;
    LocalDateTime emittedDate;
}
