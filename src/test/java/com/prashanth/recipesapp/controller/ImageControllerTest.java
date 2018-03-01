package com.prashanth.recipesapp.controller;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.service.ImageService;
import com.prashanth.recipesapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private ImageService imageService;

    private ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void showImageForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("/recipe/imageUploadForm"));
        verify(recipeService,times(1)).getRecipeCommandById(anyLong());
    }

    @Test
    public void handleImageUpload() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imageFile",
                "test.txt","text/plain","Prashanth".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService,times(1)).saveImageFile(anyLong(),any());
    }


    @Test
    public void renderImage() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String s ="Some Bytes Here";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];
        int i = 0;
        for(Byte b:s.getBytes()){
            bytesBoxed[i++] = b;
        }
        recipeCommand.setImage(bytesBoxed);

        when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(s.getBytes().length,responseBytes.length);
    }

    @Test
    public void handleNumberError() throws Exception {
        mockMvc.perform(get("/recipe/as/image"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("genericerrorpage"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attributeExists("errorCode"));
    }
}