package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.CategoryCommand;
import com.prashanth.recipesapp.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {
    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);
    private CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new Category()));
    }
    @Test
    public void convert() {
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand command = converter.convert(category);
        assertEquals(LONG_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());

    }}