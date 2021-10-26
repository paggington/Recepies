package com.example.recepies.DbHelper;

import com.example.recepies.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("RecipesRepository")
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    @Query("select max(o.id) from Recipe o")
    public Long getLastIDFromDB();
}
