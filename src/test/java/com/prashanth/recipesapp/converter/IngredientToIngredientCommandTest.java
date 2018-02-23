package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.model.Ingredient;
import com.prashanth.recipesapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientToIngredientCommandTest {

    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(10);
    public static final Long LONG_VALUE = new Long(1L);
    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvert(){
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setId(LONG_VALUE);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(LONG_VALUE);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command.getUnitOfMeasure());
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(LONG_VALUE,command.getUnitOfMeasure().getId());

    }
    @Test
    public void testConvertWithNoUOM() {
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setId(LONG_VALUE);

        IngredientCommand command = converter.convert(ingredient);

        assertNull(command.getUnitOfMeasure());
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}