package com.example.recepies.Services;

import com.example.recepies.DbHelper.RecipeRepository;
import com.example.recepies.entities.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recepyRepository;

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
    public Recipe getItemByID(Long id){
        return recepyRepository.getById(id);
    }
    public List<String> getIngredientsById(Long id){
       return recepyRepository.getById(id).getIngredients();
    }
    public List<Recipe> searchWithCriteria(boolean isHot, boolean ing, String searchField) {
        List<Recipe> allRecipes=recepyRepository.findAll();
        searchField=searchField.toLowerCase();
        //для отсева по имени
        if(!ing && searchField!=null && !isHot){
            String finalSearchField = searchField;
            allRecipes.removeIf(recipe->!recipe.getName().toLowerCase().contains(finalSearchField));
            return getHotOnTop(allRecipes);
            //для отсева по ингредиентам
        }else if(searchField!=null&& ing){
            List<Recipe> filteredRecipes=new ArrayList<>();
            if(!searchField.endsWith(",")){
                searchField+=",";
            }
            String buffer_string="";
            List<String> ingList=new ArrayList<>();
            for(int i=0;i<searchField.length();i++){
                if(searchField.charAt(i)==',' || searchField.charAt(i)==' '){
                    ingList.add(buffer_string);
                    buffer_string="";
                }
                buffer_string+=searchField.charAt(i);
            }
            for (Recipe recipe:allRecipes){
                int points=0;
                for (String ingre:recipe.getIngredients()){
                    if(ingList.contains(ingre.toLowerCase())){
                        points++;
                    }
                }
                if(points>=1){
                    filteredRecipes.add(recipe);
                }
            }
            return filteredRecipes;
        }
        return null;
    }
    public List<Recipe> getHotOnTop(List<Recipe> list){
        list.sort(Comparator.comparing(Recipe::isHot).reversed());
        return list;
    }
}
