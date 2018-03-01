package com.prashanth.recipesapp.controller;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.exception.NotFoundException;
import com.prashanth.recipesapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;
    private final String RECIPE_SAVERECIPE_URL="recipe/saveRecipe";

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
        log.info("RecipeController : autowired RecipeService");
    }

    @GetMapping({"recipes","","/","index"})
    public String showRecipes(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        log.info("RecipeController : populated model, returning view name");
        return "showrecipes";
    }

    @GetMapping("recipe/{id}/show")
    public String showRecipeById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String showRecipeForm(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_SAVERECIPE_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeCommandById(new Long(id)));
        return RECIPE_SAVERECIPE_URL;
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_SAVERECIPE_URL;
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        recipeService.deleteById(new Long(id));
        return "redirect:/recipes";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);


        return modelAndView;
    }



}
