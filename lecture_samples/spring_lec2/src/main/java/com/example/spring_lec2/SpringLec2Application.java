package com.example.spring_lec2;

import com.example.spring_lec2.domain.model.MyGreeting;
import com.example.spring_lec2.domain.model.PrintWithComma;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLec2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringLec2Application.class, args);
        PrintWithComma printer=new PrintWithComma();
        printer.print("Hello");

//        dependancy injection
        MyGreeting g=new MyGreeting(printer);
        g.print("hello");
    }

}
