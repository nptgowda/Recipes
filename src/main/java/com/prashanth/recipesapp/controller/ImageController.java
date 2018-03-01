package com.prashanth.recipesapp.controller;

import com.prashanth.recipesapp.command.RecipeCommand;
import com.prashanth.recipesapp.service.ImageService;
import com.prashanth.recipesapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Slf4j
public class ImageController {

    private ImageService imageService;
    private RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showImageForm(Model model, @PathVariable String recipeId){
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(Long.valueOf(recipeId));
        model.addAttribute("recipe", recipeCommand);
        return "/recipe/imageUploadForm";
    }
    @PostMapping("recipe/{recipeId}/image")
    public String handleImageUpload(@PathVariable String recipeId, @RequestParam("imageFile")MultipartFile multipartFile){
        imageService.saveImageFile(Long.valueOf(recipeId),multipartFile);
        log.info("********" + multipartFile.getName());
        return "redirect:/recipe/"+recipeId+"/show";
    }

    @GetMapping("recipe/{recipeId}/recipeimage")
    public String renderImage(@PathVariable Long recipeId, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(recipeId);
        if(recipeCommand.getImage() !=null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;
            for(byte b : recipeCommand.getImage()){
                byteArray[i++] = b;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());

        }
       return "none";
    }

}
