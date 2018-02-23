package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter,
                                 CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
       if(recipeCommand == null)
         return null;

       Recipe recipe = new Recipe();
       recipe.setId(recipeCommand.getId());
       recipe.setCookTime(recipeCommand.getCookTime());
       recipe.setPrepTime(recipeCommand.getPrepTime());
       recipe.setDescription(recipeCommand.getDescription());
       recipe.setDifficulty(recipeCommand.getDifficulty());
       recipe.setDirections(recipeCommand.getDirections());
       recipe.setServings(recipeCommand.getServings());
       recipe.setSource(recipeCommand.getSource());
       recipe.setUrl(recipeCommand.getUrl());

       recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

       if(recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
           recipeCommand.getCategories().forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));
       }
       if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
           recipeCommand.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
       }
       return recipe;
    }
}
