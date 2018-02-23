package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.CategoryCommand;
import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.command.NotesCommand;
import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.model.Difficulty;
import com.prashanth.recipesapp.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

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

    private RecipeCommandToRecipe converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory(),new NotesCommandToNotes());
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }
    @Test
    public void convert() {
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setCookTime(COOK_TIME);
        command.setDescription(DESCRIPTION);
        command.setDifficulty(DIFFICULTY);
        command.setDirections(DIRECTIONS);
        command.setPrepTime(PREP_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);

        Set<CategoryCommand> categories = new HashSet<>();
        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID2);
        categories.add(categoryCommand1);
        categories.add(categoryCommand2);

        command.setCategories(categories);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        command.setNotes(notesCommand);

        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGRED_ID_2);
        ingredientCommands.add(ingredientCommand1);
        ingredientCommands.add(ingredientCommand2);
        command.setIngredients(ingredientCommands);

        Recipe recipe = converter.convert(command);

        assertEquals(RECIPE_ID,recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(RECIPE_ID, recipe.getNotes().getRecipe().getId());

    }

    @Test
    public void testConvertNoDependency(){
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);

        Recipe recipe = converter.convert(command);

        assertEquals(RECIPE_ID,recipe.getId());
        assertNull(recipe.getNotes());
    }
}