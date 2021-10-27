package com.example.recepies.controllers;

import com.example.recepies.Services.RecipeService;
import com.example.recepies.entities.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipies")
public class RecipePageController {
    private final RecipeService recipeService;
    @Autowired
    public RecipePageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String getRecipePage(@PathVariable("id")Long id, Model model){
        Recipe recipe=recipeService.getItemByID(id);
        if(recipe.getFilepath()==null){
            recipe.setFilepath("/static/pictures/16/16.png");
        }
        recipe.setFilepath("/"+recipe.getFilepath());
        model.addAttribute("recipe",recipe);
        return "RecipePage/RecipePage";
    }
}
