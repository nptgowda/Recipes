package com.prashanth.recipesapp.util;

import com.prashanth.recipesapp.model.*;
import com.prashanth.recipesapp.repository.CategoryRepository;
import com.prashanth.recipesapp.repository.RecipeRepository;
import com.prashanth.recipesapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public DevBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        log.info("DevBootstrap : autowired all repositories");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);
        Optional<UnitOfMeasure> teaspoonUOM = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon");
        if(!teaspoonUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Teaspoon");
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> tablespoonUOM = unitOfMeasureRepository.findByUnitOfMeasure("Tablespoon");
        if(!tablespoonUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Tablespoon");
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> cupUOM = unitOfMeasureRepository.findByUnitOfMeasure("Cup");
        if(!cupUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Cup");
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> ounceUOM = unitOfMeasureRepository.findByUnitOfMeasure("Ounce");
        if(!ounceUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Ounce");
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> dashUOM = unitOfMeasureRepository.findByUnitOfMeasure("Dash");
        if(!dashUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Dash");
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> eachUOM = unitOfMeasureRepository.findByUnitOfMeasure("Each");
        if(!eachUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Each");
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> poundUOM = unitOfMeasureRepository.findByUnitOfMeasure("Pounds");
        if(!poundUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Pounds");
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> pintUOM = unitOfMeasureRepository.findByUnitOfMeasure("Pint");
        if(!pintUOM.isPresent()){
            log.error("DevBootstrap : UnitOfMeasure not present: Pint");
            throw new RuntimeException("Expected UOM not found");
        }

        UnitOfMeasure teaspoon = teaspoonUOM.get();
        UnitOfMeasure tablespoon = tablespoonUOM.get();
        UnitOfMeasure cup = cupUOM.get();
        UnitOfMeasure dash = dashUOM.get();
        UnitOfMeasure ounce = ounceUOM.get();
        UnitOfMeasure each = eachUOM.get();
        UnitOfMeasure pounds = poundUOM.get();
        UnitOfMeasure pint = pintUOM.get();

        Optional<Category> americanCategory = categoryRepository.findByDescription("American");
        if(!americanCategory.isPresent()){
            log.error("DevBootstrap : Category not present: American");
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> mexicanCategory = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategory.isPresent()){
            log.error("DevBootstrap : Category not present: Mexican");
            throw new RuntimeException("Expected Category not found");
        }

        Category american = americanCategory.get();
        Category mexican = mexicanCategory.get();

        Recipe guac = new Recipe();
        guac.setDescription("Guacamole");
        guac.setCookTime(0);
        guac.setDifficulty(Difficulty.EASY);
        guac.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. " +
                "Remove seed. Score the inside of the avocado with a blunt knife and " +
                "scoop out the flesh with a spoon.Place in a bowl.\n\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. " +
                "(Don't overdo it! The guacamole should be a little chunky.)" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. " +
                "The acid in the lime juice will provide some balance to the richness of the avocado " +
                "and will help delay the avocados from turning brown.\n\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary " +
                "individually in their hotness. So, start with a half of one chili pepper and " +
                "add to the guacamole to your desired degree of hotness.\n\n" +
                "Remember that much of this is done to taste because of the variability in the " +
                "fresh ingredients. Start with this recipe and adjust to your taste.\n\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of " +
                "the guacamole cover it and to prevent air reaching it. (The oxygen in the air " +
                "causes oxidation which will turn the guacamole brown.) Refrigerate until ready to " +
                "serve.\n\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your " +
                "guacamole, add it just before serving.");
        Notes notes = new Notes();
        notes.setNotes("Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches " +
                "in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries " +
                "(see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. " +
                "Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage " +
                "cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");
        guac.setNotes(notes);
        guac.setPrepTime(10);
        guac.setServings(4);
        guac.getCategories().add(american);
        guac.getCategories().add(mexican);

        guac.addIngredient(new Ingredient("ripe avocados",new BigDecimal(2),each));
        guac.addIngredient(new Ingredient("Kosher salt",new BigDecimal(.5),teaspoon));
        guac.addIngredient(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(1),tablespoon));
        guac.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(2),tablespoon));
        guac.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced",new BigDecimal(2),each));
        guac.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped",new BigDecimal(2),tablespoon));
        guac.addIngredient(new Ingredient("freshly grated black pepper",new BigDecimal(1),dash));
        guac.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped",new BigDecimal(.5),each));

        guac.setSource("Simply Recipes");
        guac.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        recipes.add(guac);

        Recipe taco = new Recipe();
        taco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        taco.setSource("Simply Recipes");
        Notes tacoNotes = new Notes();
        tacoNotes.setNotes("Look for ancho chile powder with the Mexican ingredients " +
                "at your grocery store, on buy it online. (If you can't find ancho chili powder," +
                " you replace the ancho chili, the oregano, " +
                "and the cumin with 2 1/2 tablespoons regular chili powder, " +
                "though the flavor won't be quite the same.)");
        taco.setNotes(tacoNotes);
        taco.setServings(6);
        taco.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        taco.setDifficulty(Difficulty.MODERATE);
        taco.setCookTime(15);
        taco.setPrepTime(20);
        taco.getCategories().add(american);
        taco.getCategories().add(mexican);
        taco.setDescription("Spicy grilled chicken tacos.");
        taco.addIngredient(new Ingredient("ancho chili powder",new BigDecimal(2),tablespoon));
        taco.addIngredient(new Ingredient("dried oregano",new BigDecimal(1),teaspoon));
        taco.addIngredient(new Ingredient("dried cumin",new BigDecimal(1),teaspoon));
        taco.addIngredient(new Ingredient("sugar",new BigDecimal(1),teaspoon));
        taco.addIngredient(new Ingredient("salt",new BigDecimal(.5),teaspoon));
        taco.addIngredient(new Ingredient("clove garlic, finely chopped\n",new BigDecimal(1),each));
        taco.addIngredient(new Ingredient("finely grated orange zest",new BigDecimal(1),tablespoon));
        taco.addIngredient(new Ingredient("fresh-squeezed orange juice",new BigDecimal(3),tablespoon));
        taco.addIngredient(new Ingredient("olive oil",new BigDecimal(2),tablespoon));
        taco.addIngredient(new Ingredient("skinless, boneless chicken thighs",new BigDecimal(1.25),pounds));
        taco.addIngredient(new Ingredient("small corn tortillas",new BigDecimal(8),each));
        taco.addIngredient(new Ingredient("packed baby arugula",new BigDecimal(3),ounce));
        taco.addIngredient(new Ingredient("medium ripe avocados, sliced",new BigDecimal(2),each));
        taco.addIngredient(new Ingredient("radishes, thinly sliced",new BigDecimal(4),each));
        taco.addIngredient(new Ingredient("cherry tomatoes, halved",new BigDecimal(4),pint));
        taco.addIngredient(new Ingredient("red onion, thinly sliced",new BigDecimal(.25),each));
        taco.addIngredient(new Ingredient("Roughly chopped cilantro",new BigDecimal(1),each));
        taco.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk",new BigDecimal(.5),cup));
        taco.addIngredient(new Ingredient("lime, cut into wedges",new BigDecimal(1),each));
        recipes.add(taco);
        log.info("DevBootstrap : added all recipes to recipe list");
        return recipes;
    }
}
