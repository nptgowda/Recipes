package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.CategoryCommand;
import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.command.NotesCommand;
import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    private RecipeToRecipeCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(),new NotesToNotesCommand());
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new Recipe()));
    }
    @Test
    public void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setPrepTime(PREP_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Set<Category> categories = new HashSet<>();
        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID2);
        categories.add(category1);
        categories.add(category2);

        recipe.setCategories(categories);

        Notes notes= new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        recipe.setIngredients(ingredients);

        RecipeCommand command = converter.convert(recipe);

        assertEquals(RECIPE_ID,command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());

    }

    @Test
    public void testConvertNoDependency(){
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);

        RecipeCommand command = converter.convert(recipe);

        assertEquals(RECIPE_ID,command.getId());
        assertNull(command.getNotes());
    }

}