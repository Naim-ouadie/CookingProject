package fr.bonitasoft.cookingproject.controller;

import fr.bonitasoft.cookingproject.entities.Ingredient;
import fr.bonitasoft.cookingproject.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredientDetails) {
        Ingredient updatedIngredient = ingredientService.updateIngredient(id, ingredientDetails);
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok().build();
    }
}