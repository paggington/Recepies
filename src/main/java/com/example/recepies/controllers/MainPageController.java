package com.example.recepies.controllers;

import com.example.recepies.Services.RecepyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private final RecepyService recepyService;
    @Autowired
    public MainPageController(RecepyService recepyService) {
        this.recepyService = recepyService;
    }
    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("recipes",recepyService.getAllIsHot());
        return "MainPage/MainPage";
    }
}
