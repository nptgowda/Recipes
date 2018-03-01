package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.converter.IngredientCommandToIngredient;
import com.prashanth.recipesapp.converter.IngredientToIngredientCommand;
import com.prashanth.recipesapp.converter.UnitOfMeasureCommandToUnitOfMeasure;
import com.prashanth.recipesapp.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.prashanth.recipesapp.model.Ingredient;
import com.prashanth.recipesapp.model.Recipe;
import com.prashanth.recipesapp.repository.IngredientRepository;
import com.prashanth.recipesapp.repository.RecipeRepository;
import com.prashanth.recipesapp.repository.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private IngredientService ingredientService;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    private IngredientRepository ingredientRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        ingredientService = new IngredientServiceImpl(recipeRepository,ingredientToIngredientCommand,
                ingredientCommandToIngredient,
                unitOfMeasureRepository, ingredientRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand= ingredientService.findByRecipeIdAndIngredientId(recipe.getId(),ingredient1.getId());
        assertEquals(Long.valueOf(1L),ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository,times(1)).findById(anyLong());
    }

       @Test
    public void deleteIngredientById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        recipe.addIngredient(ingredient);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ingredientService.deleteIngredientById(ingredient.getId(),recipe.getId());

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));

    }
}