package com.example.recepies.Services;

import com.example.recepies.DbHelper.RecepyRepository;
import com.example.recepies.entities.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecepyService {
    @Autowired
    private RecepyRepository recepyRepository;

    //getting List of Recipes From All Users Which marked as Hot
    public List<Recipe> getAllIsHot(){
        List<Recipe> HotRecipes=new ArrayList<Recipe>();
        List<Recipe> Recipes=new ArrayList<Recipe>();
        for(Recipe recipe:recepyRepository.findAll()){
            if (recipe.isHot()){
                HotRecipes.add(recipe);
            }else{
                Recipes.add(recipe);
            }
        }
        for(Recipe recipe:Recipes){
            HotRecipes.add(recipe);
        }
        return HotRecipes;
    }
    //TODO:Make a validation
    public void saveNewRecipe(Recipe recipe){
        recepyRepository.save(recipe);
    }
    public Long getMaxIDFromDb(){
        return recepyRepository.getLastIDFromDB();
    }
    public List<String> getIngredients(String string){
        string+=',';
        List<String> ingredients=new ArrayList<>();
        String buffer_string="";
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)!=','){
                buffer_string+=string.charAt(i);
            }
            else{
                ingredients.add(buffer_string.substring(0, 1).toUpperCase() + buffer_string.substring(1));
                buffer_string="";
            }
        }
        return ingredients;
    }

}
