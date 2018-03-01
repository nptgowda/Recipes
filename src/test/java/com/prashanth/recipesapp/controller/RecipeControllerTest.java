package com.prashanth.recipesapp.controller;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.exception.NotFoundException;
import com.prashanth.recipesapp.model.Recipe;
import com.prashanth.recipesapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;
    MockMvc mockMvc;
    RecipeController controller;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void mockMvcTest() throws Exception {

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("showrecipes"));
    }

    @Test
    public void showRecipes() {
        //given
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        //when
        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);
        String viewName = controller.showRecipes(model);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        //then
        assertEquals("showrecipes",viewName);
        Mockito.verify(recipeService,Mockito.times(1)).getRecipes();
        Mockito.verify(model,Mockito.times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> controllerSet = argumentCaptor.getValue();
        assertEquals(2,controllerSet.size());
    }


    @Test
    public void showRecipeById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Mockito.when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testAddNewRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);
        Mockito.when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description","desc String")
                .param("directions","Some Direction"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));

    }

    @Test
    public void testNewRecipeValidators() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .param("id","")
            )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/saveRecipe"));

    }

    @Test
    public void testUpdateRecipeView() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);

        Mockito.when(recipeService.getRecipeCommandById(any())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/saveRecipe"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes"));
    }

    @Test
    public void handleNotFound() throws Exception {
        when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/5/show"))
                .andExpect(status().isNotFound())
                .andExpect(model().attributeExists("exception"))
                .andExpect(view().name("404error"));
    }

    @Test
    public void handleNumberError() throws Exception {
        mockMvc.perform(get("/recipe/as/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("genericerrorpage"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attributeExists("errorCode"));
    }
}