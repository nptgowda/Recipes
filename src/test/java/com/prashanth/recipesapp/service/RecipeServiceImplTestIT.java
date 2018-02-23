package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.converter.RecipeCommandToRecipe;
import com.prashanth.recipesapp.converter.RecipeToRecipeCommand;
import com.prashanth.recipesapp.model.Recipe;
import com.prashanth.recipesapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplTestIT {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;

    private final Long RECIPE_ID = 1L;
    private final String DIRECTIONS = "cook the food";


    @Before
    public void setUp() throws Exception {
        recipeService = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    @Transactional
    public void getRecipes() {
        Iterable<Recipe> recipes = recipeService.getRecipes();
        assertNotNull(recipes);
        Recipe recipe = recipes.iterator().next();
        assertNotNull(recipe.getId());
    }

    @Test
    @Transactional
    public void getRecipeById() {
        Recipe recipe = recipeService.getRecipeById(RECIPE_ID);
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
    }

    @Test
    @Transactional
    public void saveRecipeCommand() {
        Iterable<Recipe> recipes = recipeService.getRecipes();
        Iterator<Recipe> itr = recipes.iterator();
        Long lastId = 1L;
        while(itr.hasNext()){
            lastId = itr.next().getId();
        }
        Long saveId = lastId + 1;
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(saveId);
        recipeCommand.setDirections(DIRECTIONS);

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
        assertNotNull(savedCommand);
        assertEquals(saveId,savedCommand.getId());
        assertEquals(DIRECTIONS,savedCommand.getDirections());
    }
}