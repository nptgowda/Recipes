package com.prashanth.recipesapp.repository;

import com.prashanth.recipesapp.model.Ingredient;
import com.prashanth.recipesapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {

    public Optional<Ingredient> findByDescriptionAndAmountAndRecipe(String description, BigDecimal amount, Recipe Recipe);
}
