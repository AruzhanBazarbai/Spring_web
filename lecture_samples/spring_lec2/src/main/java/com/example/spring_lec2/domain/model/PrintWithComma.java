package com.example.spring_lec2.domain.model;

import com.example.spring_lec2.domain.model.Printable;

public class PrintWithComma implements Printable {
    public void print( String message){
        System.out.print(message+",");
    }
}
