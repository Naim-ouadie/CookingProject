package fr.bonitasoft.cookingproject.service;


import fr.bonitasoft.cookingproject.entities.Recipe;
import fr.bonitasoft.cookingproject.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> searchRecipesByIngredient(String ingredientName) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name must not be empty");
        }

        List<Recipe> recipes = recipeRepository.findByIngredientsName(ingredientName);
        if (recipes.isEmpty()) {
            throw new EntityNotFoundException("No recipes found with ingredient: " + ingredientName);
        }

        return recipes;
    }


    public List<Recipe> searchRecipesByKeyword(String keywordName) {
        if (keywordName == null || keywordName.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword name must not be empty");
        }

        List<Recipe> recipes = recipeRepository.findByKeywordsName(keywordName);
        if (recipes.isEmpty()) {
            throw new EntityNotFoundException("No recipes found with keyword: " + keywordName);
        }

        return recipes;
    }

    public List<Recipe> searchRecipesByAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name must not be empty");
        }

        List<Recipe> recipes = recipeRepository.findByAuthor_AuthorName(authorName);
        if (recipes.isEmpty()) {
            throw new EntityNotFoundException("No recipes found for author: " + authorName);
        }

        return recipes;
    }

    public Recipe createRecipe(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe cannot be null");
        }
        if (recipe.getDescription() == null || recipe.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Recipe description cannot be empty");
        }

        try {
            return recipeRepository.save(recipe);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Error saving the recipe: " + e.getMessage(), e);
        }
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No recipe found with ID: " + id));
    }


    public List<Recipe> getAllRecipes() {
        try {
            return recipeRepository.findAll();
        } catch (DataAccessException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve recipes");
        }
    }

    public Recipe updateRecipe(Long id, Recipe recipeDetails) {
        if (recipeDetails == null) {
            throw new IllegalArgumentException("Recipe details cannot be null");
        }

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found with ID: " + id));
        if (recipeDetails.getDescription() == null) {
            throw new IllegalArgumentException("Recipe description cannot be null");
        }

        recipe.setDescription(recipeDetails.getDescription());
        recipe.setKeywords(recipeDetails.getKeywords());
        recipe.setAuthor(recipeDetails.getAuthor());

        return recipeRepository.save(recipe);
    }


    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new EntityNotFoundException("No recipe found with ID: " + id);
        }
        recipeRepository.deleteById(id);
    }


}