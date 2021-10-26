package com.example.recepies.controllers;

import com.example.recepies.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private final RecipeService recipeService;
    @Autowired
    public MainPageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("recipes", recipeService.getAllIsHot());
        return "MainPage/MainPage";
    }
}
