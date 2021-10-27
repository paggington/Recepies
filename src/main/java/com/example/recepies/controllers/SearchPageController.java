package com.example.recepies.controllers;

import com.example.recepies.Services.RecipeService;
import com.example.recepies.entities.Recipe;
import com.example.recepies.entities.RequestCoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchPageController {
    private final RecipeService recipeService;
    @Autowired
    public SearchPageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String getSearchPage(@ModelAttribute("request")RequestCoEntity requestCoEntity, Model model){
        model.addAttribute("request",new RequestCoEntity());
        model.addAttribute("recipes",recipeService.searchWithCriteria(requestCoEntity.isHot(), requestCoEntity.isIngs(),requestCoEntity.getField()));
        return "SearchPage/SearchPage";
    }
    @PostMapping
    public String  searchForRecipes(RedirectAttributes redirectAttributes, @ModelAttribute("request") RequestCoEntity requestCoEntity){
        redirectAttributes.addAttribute("isHot",requestCoEntity.isHot());
        redirectAttributes.addAttribute("ings",requestCoEntity.isIngs());
        redirectAttributes.addAttribute("field",requestCoEntity.getField());
        return "redirect:/search";
    }
}
