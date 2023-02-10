package com.example.sis3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tutorials_test")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="t_id")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="published")
    private Boolean published;

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }
}
