package com.prashanth.recipesapp.controller;

import com.prashanth.recipesapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
        log.info("RecipeController : autowired RecipeService");
    }

    @RequestMapping("recipes")
    public String showRecipes(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        log.info("RecipeController : populated model, returning view name");
        return "showrecipes";
    }
}
