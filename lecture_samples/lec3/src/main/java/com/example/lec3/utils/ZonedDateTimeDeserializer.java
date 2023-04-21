package com.example.lec3.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        ZonedDateTime userDateTime=ZonedDateTime.parse(p.getText(), dateTimeFormatter);
//        ZonedDateTime serverTime=userDateTime.withZoneSameInstant(ZoneId.systemDefault());
//        System.out.println(serverTime.toLocalDateTime());
        return ZonedDateTime.parse(p.getText(), dateTimeFormatter);
    }}