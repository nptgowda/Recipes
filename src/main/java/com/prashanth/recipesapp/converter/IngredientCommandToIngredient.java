package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.model.Ingredient;
import org.springframework.core.convert.converter.Converter;

public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter) {
        this.uomConverter = converter;
    }

    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null)
            return null;
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setUnitOfMeasure(uomConverter.convert(ingredientCommand.getUnitOfMeasure()));
        return ingredient;
    }
}
