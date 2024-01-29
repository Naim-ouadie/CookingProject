import fr.bonitasoft.cookingproject.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import fr.bonitasoft.cookingproject.entities.Recipe;
import fr.bonitasoft.cookingproject.repository.RecipeRepository;
import org.springframework.dao.DataAccessException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe;

    @BeforeEach
    void setup() {
        recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription("Test Recipe");
    }

    @Test
    void testCreateRecipe() {
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Recipe savedRecipe = recipeService.createRecipe(recipe);

        assertNotNull(savedRecipe);
        assertEquals("Test Recipe", savedRecipe.getDescription());
    }

    @Test
    void testGetAllRecipes() {
        List<Recipe> recipes = Collections.singletonList(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> result = recipeService.getAllRecipes();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllRecipesException() {
        when(recipeRepository.findAll()).thenThrow(new DataAccessException("Error") {
        });

        List<Recipe> result = recipeService.getAllRecipes();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetRecipeById() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe foundRecipe = recipeService.getRecipeById(1L);

        assertNotNull(foundRecipe);
        assertEquals(1L, foundRecipe.getId());
    }

    @Test
    void testGetRecipeByIdNotFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            recipeService.getRecipeById(1L);
        });
    }

    @Test
    void testDeleteRecipe() {
        when(recipeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(recipeRepository).deleteById(1L);

        recipeService.deleteRecipe(1L);

        verify(recipeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteRecipeNotFound() {
        when(recipeRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            recipeService.deleteRecipe(1L);
        });
    }


}
