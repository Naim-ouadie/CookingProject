import fr.bonitasoft.cookingproject.controller.RecipeController;
import fr.bonitasoft.cookingproject.entities.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import fr.bonitasoft.cookingproject.service.RecipeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void testGetAllRecipes() throws Exception {
        List<Recipe> recipes = Collections.singletonList(new Recipe());
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        mockMvc.perform(get("/api/recipes/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testCreateRecipe() throws Exception {
        Recipe newRecipe = new Recipe();
        newRecipe.setDescription("New Recipe");
        when(recipeService.createRecipe(any(Recipe.class))).thenReturn(newRecipe);

        mockMvc.perform(post("/api/recipes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"New Recipe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("New Recipe"));
    }

    @Test
    void testSearchRecipesByIngredient() throws Exception {
        List<Recipe> recipes = Collections.singletonList(new Recipe());
        when(recipeService.searchRecipesByIngredient(anyString())).thenReturn(recipes);

        mockMvc.perform(get("/api/recipes/search/ingredient")
                        .param("ingredient", "Tomato"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.getRecipeById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDeleteRecipe() throws Exception {
        doNothing().when(recipeService).deleteRecipe(1L);

        mockMvc.perform(delete("/api/recipes/{id}", 1))
                .andExpect(status().isOk());
    }

}
