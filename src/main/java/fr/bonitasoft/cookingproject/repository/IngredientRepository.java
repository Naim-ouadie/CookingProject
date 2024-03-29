package fr.bonitasoft.cookingproject.repository;

import fr.bonitasoft.cookingproject.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

}
