package fr.bonitasoft.cookingproject.repository;

import fr.bonitasoft.cookingproject.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByAuthor_AuthorName(String authorName);

    List<Recipe> findByKeywordsName(String keyword);

    @Query("SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.name LIKE %:ingredientName%")
    List<Recipe> findByIngredientsName(String ingredientName);

}
