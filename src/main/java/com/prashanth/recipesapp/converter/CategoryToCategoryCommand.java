package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.CategoryCommand;
import com.prashanth.recipesapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {


    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if(category == null)
            return null;

        CategoryCommand command = new CategoryCommand();
        command.setId(category.getId());
        command.setDescription(category.getDescription());
        return command;
    }
}
