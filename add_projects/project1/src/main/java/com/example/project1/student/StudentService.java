package com.example.project1.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Service
public class StudentService{
    public List<Student> getStudents(){
        return List.of(new Student(2L,"Aruzhan","aaaaaa", LocalDate.of(2000, Month.APRIL,24),21));
    }
}
