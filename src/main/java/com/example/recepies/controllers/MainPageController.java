package com.example.recepies.controllers;

import com.example.recepies.DbHelper.MyUserDS;
import com.example.recepies.Services.RecipeService;
import com.example.recepies.entities.AppUser;
import com.example.recepies.entities.RequestCoEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class MainPageController {
    @Autowired
    private final RecipeService recipeService;
    @Autowired
    private final MyUserDS myUserDS;

    @GetMapping("/")
    public String getMainPage(Model model, Authentication authentication){
        model.addAttribute("request",new RequestCoEntity());
        model.addAttribute("recipes", recipeService.getAllIsHot());
        if(authentication!=null){
            model.addAttribute("check",true);
            model.addAttribute("user",myUserDS.getUserByUsername(authentication.getName()));
        }else{
            model.addAttribute("check",false);
        }
        return "MainPage/MainPage";
    }
    @PostMapping("/")
    public String  searchForRecipes(RedirectAttributes redirectAttributes, @ModelAttribute("request")RequestCoEntity requestCoEntity){
        redirectAttributes.addAttribute("isHot",requestCoEntity.isHot());
        redirectAttributes.addAttribute("ings",requestCoEntity.isIngs());
        redirectAttributes.addAttribute("field",requestCoEntity.getField());
        return "redirect:/search";
    }
}
