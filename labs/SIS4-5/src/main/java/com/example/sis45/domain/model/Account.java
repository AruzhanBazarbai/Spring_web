package com.example.sis45.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="acc_id")
    private long id;

    @Column(name="name")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @CreationTimestamp
    @Column(name="created_on")
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private LocalDateTime created_on;

    @Column(name="last_login")
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private LocalDateTime last_login;

//    @Column(name="created_on")
//    private LocalDateTime created_on;
}