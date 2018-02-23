package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.UnitOfMeasureCommand;
import com.prashanth.recipesapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String UNIT_OF_MEASURE = "unitofmeasure";
    public static final Long LONG_VALUE = new Long(1L);
    private UnitOfMeasureToUnitOfMeasureCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(LONG_VALUE);
        unitOfMeasure.setUnitOfMeasure(UNIT_OF_MEASURE);

        UnitOfMeasureCommand command = converter.convert(unitOfMeasure);
        assertEquals(LONG_VALUE,command.getId());
        assertEquals(UNIT_OF_MEASURE,command.getUnitOfMeasure());
    }
}