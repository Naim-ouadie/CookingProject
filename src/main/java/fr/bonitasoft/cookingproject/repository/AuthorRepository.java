package fr.bonitasoft.cookingproject.repository;

import fr.bonitasoft.cookingproject.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
