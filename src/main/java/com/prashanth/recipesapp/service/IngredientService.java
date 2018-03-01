package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.model.Ingredient;

import java.util.Set;

public interface IngredientService {
    public Set<Ingredient> findIngredients();

    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);

    public IngredientCommand saveIngredient(IngredientCommand ingredientCommand);

    public void deleteIngredientById(Long idToDelete, Long recipeId);
}
