package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
