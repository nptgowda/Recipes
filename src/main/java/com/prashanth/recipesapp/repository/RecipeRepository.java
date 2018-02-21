package com.prashanth.recipesapp.repository;

import com.prashanth.recipesapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
