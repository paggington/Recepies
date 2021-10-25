package com.example.recepies.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "receipts_table")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    private float rating=0.0F;
    private Integer numberOfViews=0;
    @Column(nullable = false)
    private String dateOfPublishing=new AppUser().getCurrentDate();
    @Column(nullable = false)
    private String username;
    private boolean isHot=false;
    private String filepath="";
    @ElementCollection
    public List<String> ingredients;

    public Recipe(String name, String description, float rating, Integer numberOfViews, String dateOfPublishing, String username, boolean isHot, String filepath, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.numberOfViews = numberOfViews;
        this.dateOfPublishing = dateOfPublishing;
        this.username = username;
        this.isHot = isHot;
        this.filepath = filepath;
        this.ingredients = ingredients;
    }

    public Recipe() {
    }
}
