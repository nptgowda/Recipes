package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.command.UnitOfMeasureCommand;
import com.prashanth.recipesapp.model.Ingredient;
import com.prashanth.recipesapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(10);
    public static final Long LONG_VALUE = new Long(1L);
    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        IngredientCommand command = new IngredientCommand();
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        command.setId(LONG_VALUE);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(LONG_VALUE);
        command.setUnitOfMeasure(unitOfMeasureCommand);

        Ingredient ingredient = converter.convert(command);
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(LONG_VALUE,ingredient.getUnitOfMeasure().getId());

    }

    @Test
    public void convertWithNullUOM(){
        IngredientCommand command = new IngredientCommand();
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        command.setId(LONG_VALUE);

        Ingredient ingredient = converter.convert(command);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION,ingredient.getDescription());

    }

}