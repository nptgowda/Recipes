package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
