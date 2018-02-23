package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.CategoryCommand;
import com.prashanth.recipesapp.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);
    private CategoryCommandToCategory converter;
    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }
    @Test
    public void convert() {
        CategoryCommand command = new CategoryCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        Category category = converter.convert(command);
        assertEquals(LONG_VALUE,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());

    }
}