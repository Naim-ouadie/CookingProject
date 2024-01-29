package fr.bonitasoft.cookingproject.controller;

import fr.bonitasoft.cookingproject.entities.Recipe;
import fr.bonitasoft.cookingproject.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/search/ingredient")
    public List<Recipe> searchRecipesByIngredient(@RequestParam String ingredient) {
        return recipeService.searchRecipesByIngredient(ingredient);
    }

    @GetMapping("/search/keyword")
    public List<Recipe> searchRecipesByKeyword(@RequestParam String keyword) {
        return recipeService.searchRecipesByKeyword(keyword);
    }

    @GetMapping("/search/author")
    public List<Recipe> searchRecipesByAuthor(@RequestParam String author) {
        return recipeService.searchRecipesByAuthor(author);
    }

    @PostMapping("/")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {

        return recipeService.getRecipeById(id);
    }

    @GetMapping("/")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipeDetails);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

}