package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null)
            return null;

        IngredientCommand command = new IngredientCommand();
        command.setId(ingredient.getId());
        if(ingredient.getRecipe() != null) {
            command.setRecipeId(ingredient.getRecipe().getId());
        }
        command.setDescription(ingredient.getDescription());
        command.setAmount(ingredient.getAmount());
        command.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        return command;
    }
}
