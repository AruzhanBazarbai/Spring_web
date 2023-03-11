package com.example.sis6.domain.event;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class AccountCreatedEvent {
    String aggregateObjectType;
    String aggregateObjectId;
    String messagePayload;
    LocalDateTime emittedDate;

}
