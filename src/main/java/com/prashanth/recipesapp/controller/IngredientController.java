package com.prashanth.recipesapp.controller;

import com.prashanth.recipesapp.command.IngredientCommand;
import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.command.UnitOfMeasureCommand;
import com.prashanth.recipesapp.service.IngredientService;
import com.prashanth.recipesapp.service.RecipeService;
import com.prashanth.recipesapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(Model model, @PathVariable String recipeId){
        log.info("In IngredientController : listIngredients");
        model.addAttribute("recipe",recipeService.getRecipeCommandById(new Long(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}")
    public String showIngredient(Model model, @PathVariable String recipeId, @PathVariable String ingredientId){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId),new Long(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredientForm(@PathVariable String recipeId,@PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUnitsOfMeasure());
        return "recipe/ingredient/ingredientForm";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredient(ingredientCommand);
        return "redirect:/recipe/" + savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId();
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(Model model, @PathVariable String recipeId) {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(Long.valueOf(recipeId));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUnitsOfMeasure());

        return "recipe/ingredient/ingredientForm";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(Model model, @PathVariable String recipeId, @PathVariable String ingredientId){
        ingredientService.deleteIngredientById(Long.valueOf(ingredientId),Long.valueOf(recipeId));
        return "redirect:/recipe/"+ recipeId+"/ingredients";
    }
}
