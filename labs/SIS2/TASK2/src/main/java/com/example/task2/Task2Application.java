package com.example.task2;

import com.example.task2.hello.Greeter;
import org.joda.time.LocalTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task2Application {

    public static void main(String[] args) {
        SpringApplication.run(Task2Application.class, args);
        LocalTime curTime=new LocalTime();
        System.out.println("The current local time is: " + curTime);
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }

}
