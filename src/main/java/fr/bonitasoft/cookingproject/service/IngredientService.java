package fr.bonitasoft.cookingproject.service;

import fr.bonitasoft.cookingproject.entities.Ingredient;
import fr.bonitasoft.cookingproject.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }
        try {
            return ingredientRepository.save(ingredient);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Error saving the ingredient: " + e.getMessage(), e);
        }
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found with ID: " + id));
    }

    public List<Ingredient> getAllIngredients() {
        try {
            return ingredientRepository.findAll();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error accessing ingredient data", e);
        }
    }


    public Ingredient updateIngredient(Long id, Ingredient ingredientDetails) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found with ID: " + id));
        ingredient.setName(ingredientDetails.getName());
        ingredient.setRecipes(ingredientDetails.getRecipes());
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new EntityNotFoundException("No ingredient found with ID: " + id);
        }
        try {
            ingredientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Error deleting the ingredient: " + e.getMessage(), e);
        }
    }
}
