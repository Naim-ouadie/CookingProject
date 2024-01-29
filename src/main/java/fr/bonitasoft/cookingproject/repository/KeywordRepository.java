package fr.bonitasoft.cookingproject.repository;

import fr.bonitasoft.cookingproject.entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword,Long> {
}
