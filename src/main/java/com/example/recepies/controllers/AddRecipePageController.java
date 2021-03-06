package com.example.recepies.controllers;

import com.example.recepies.ConfigForStuff;
import com.example.recepies.DbHelper.MyUserDS;
import com.example.recepies.FileUploadUtil;
import com.example.recepies.Services.RecipeService;
import com.example.recepies.entities.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/add")
@AllArgsConstructor
public class AddRecipePageController {
    @Autowired
    private final RecipeService recipeService;
    @Autowired
    private final MyUserDS myUserDS;

    @GetMapping
    public String getAddRecipePage(Model model){
        Recipe recipe=new Recipe();
        model.addAttribute("recipe",recipe);
        return "AddRecipePage/AddRecipePage";
    }
    @PostMapping
    public String postNewRecipe(@Valid @ModelAttribute Recipe recipe,
                                @RequestParam("image") MultipartFile multipartFile,
                                @RequestParam("string-for-ingredients") String string_of_ingredients,
                                Authentication authentication
                                ){

        Long ElementIndex= recipeService.getMaxIDFromDb();

        String fileName=StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())).substring
                (multipartFile.getOriginalFilename().length()-4,multipartFile.getOriginalFilename().length());
        String filename=String.valueOf(++ElementIndex);
        String uploadDirectory="static/pictures/"+filename;

        recipe.setDateOfPublishing(new ConfigForStuff().getCurrentDateAndTime());
        recipe.setFilepath(uploadDirectory+"/"+filename+fileName);
        recipe.setHot(false);
        recipe.setIngredients(recipeService.getIngredients(string_of_ingredients));
        recipe.setNumberOfViews(1);
        recipe.setRating(0.0F);
        recipe.setUsername(authentication.getName());
        myUserDS.setNewDataAboutUser(myUserDS.getUserByUsername(authentication.getName()));
        recipeService.saveNewRecipe(recipe);
        try {
            FileUploadUtil.saveFile(uploadDirectory, filename, multipartFile,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:";
    }
}
