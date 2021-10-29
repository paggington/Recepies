package com.example.recepies.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_users")
public class AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String dateOfRegistration=getCurrentDate();
    @Column(nullable = false)
    private Integer numberOfPublications=0;
    @Column(nullable = false)
    private float AverageReceiptsScore=0.0F;
    @Column(nullable = false)
    private String lastActivityTime;
    private String role;
    private boolean isActive;

    public AppUser(String username, String password, String dateOfRegistration, Integer numberOfPublications, float averageReceiptsScore, String lastActivityTime, String role, boolean isActive) {
        this.username = username;
        this.password = password;
        this.dateOfRegistration = dateOfRegistration;
        this.numberOfPublications = numberOfPublications;
        AverageReceiptsScore = averageReceiptsScore;
        this.lastActivityTime = lastActivityTime;
        this.role = role;
        this.isActive = isActive;
    }

    public String getCurrentDate(){
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    public String getTimeOfActivity(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
