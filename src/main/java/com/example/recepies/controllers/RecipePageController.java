package com.example.recepies.controllers;

import com.example.recepies.Services.RecipeService;
import com.example.recepies.entities.Recipe;
import com.example.recepies.entities.RequestCoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipies")
public class RecipePageController {
    private final RecipeService recipeService;

    @Autowired
    public RecipePageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String getRecipePage(@PathVariable("id") Long id, Model model) {
        Recipe recipe = recipeService.getItemByID(id);
        if (recipe.getFilepath() == null) {
            recipe.setFilepath("/static/pictures/16/16.png");
        }
        recipe.setFilepath("/" + recipe.getFilepath());
        model.addAttribute("request", new RequestCoEntity());
        model.addAttribute("recipe", recipe);
        return "RecipePage/RecipePage";
    }

    @PostMapping("/")
    public String searchForRecipes(RedirectAttributes redirectAttributes, @ModelAttribute("request") RequestCoEntity requestCoEntity) {
        redirectAttributes.addAttribute("isHot", requestCoEntity.isHot());
        redirectAttributes.addAttribute("ings", requestCoEntity.isIngs());
        redirectAttributes.addAttribute("field", requestCoEntity.getField());
        return "redirect:/search";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id")Long id,RedirectAttributes redirectAttributes){
        recipeService.deleteOnId(id);
        redirectAttributes.addAttribute("id",id);
        return "redirect:/";
    }
}

