package com.example.lec3.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//    @Column(name="created_on")
//    private LocalDateTime created_on;
}
