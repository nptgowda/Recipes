package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.converter.RecipeCommandToRecipe;
import com.prashanth.recipesapp.converter.RecipeToRecipeCommand;
import com.prashanth.recipesapp.model.Recipe;
import com.prashanth.recipesapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeHashSet = new HashSet<>();
        recipeHashSet.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipeHashSet);


        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(),1);
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        //given
        Long id = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);

        //then
        Recipe returnedRecipe = recipeService.getRecipeById(id);
        assertNotNull("Null Object Returned", returnedRecipe);
        assertEquals(id,returnedRecipe.getId());
        Mockito.verify(recipeRepository,Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository, Mockito.never()).findAll();

    }
}