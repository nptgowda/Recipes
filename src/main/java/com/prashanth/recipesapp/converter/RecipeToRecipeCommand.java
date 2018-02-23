package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null)
            return null;

        RecipeCommand command = new RecipeCommand();
        command.setId(recipe.getId());
        command.setCookTime(recipe.getCookTime());
        command.setPrepTime(recipe.getPrepTime());
        command.setDescription(recipe.getDescription());
        command.setDifficulty(recipe.getDifficulty());
        command.setDirections(recipe.getDirections());
        command.setServings(recipe.getServings());
        command.setSource(recipe.getSource());
        command.setUrl(recipe.getUrl());

        command.setNotes(notesConverter.convert(recipe.getNotes()));

        if(recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories().forEach( category -> command.getCategories().add(categoryConverter.convert(category)));
        }
        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients().forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        return command;
    }
}
