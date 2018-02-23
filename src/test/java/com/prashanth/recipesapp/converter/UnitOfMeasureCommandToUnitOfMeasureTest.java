package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.UnitOfMeasureCommand;
import com.prashanth.recipesapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String UNIT_OF_MEASURE = "unitofmeasure";
    public static final Long LONG_VALUE = new Long(1L);

    private UnitOfMeasureCommandToUnitOfMeasure converter;
    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void testConverter(){
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setUnitOfMeasure(UNIT_OF_MEASURE);

        UnitOfMeasure unitOfMeasure = converter.convert(command);
        assertEquals(LONG_VALUE, unitOfMeasure.getId());
        assertEquals(UNIT_OF_MEASURE, unitOfMeasure.getUnitOfMeasure());
    }
}