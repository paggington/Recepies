package com.example.recepies.controllers;

import com.example.recepies.DbHelper.MyUserDS;
import com.example.recepies.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/new_user")
public class AddNewUserPage {
    @Autowired
    private MyUserDS myUserDS;
    @GetMapping
    public String getNewUserPage(Model model){
        model.addAttribute("user", new AppUser());
        return "AddUserPage/AddUserPage";
    }
    @PostMapping()
    public String requestToAddUser(@Valid @ModelAttribute("user")AppUser user, BindingResult bindingResult){
        if(myUserDS.loadUserByUsername(user.getUsername())!=null){
        }

        return "redirect:/";
    }
}
