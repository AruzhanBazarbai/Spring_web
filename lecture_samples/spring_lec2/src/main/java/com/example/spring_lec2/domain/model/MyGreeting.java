package com.example.spring_lec2.domain.model;

public class MyGreeting {
    Printable printer;
    public  MyGreeting(Printable printer){
        this.printer=printer;
    }
    public  void print(String message){
        printer.print(message);
    }
}
