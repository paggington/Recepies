package com.example.recepies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class ConfigForStuff {
    @Bean
    public String getCurrentDateAndTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
