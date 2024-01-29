package fr.bonitasoft.cookingproject.service;

import fr.bonitasoft.cookingproject.entities.Keyword;
import fr.bonitasoft.cookingproject.repository.KeywordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public Keyword createKeyword(Keyword keyword) {
        if (keyword == null || keyword.getName() == null || keyword.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword name cannot be null or empty");
        }
        return keywordRepository.save(keyword);
    }

    public Keyword getKeywordById(Long id) {
        return keywordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Keyword not found with ID: " + id));
    }

    public List<Keyword> getAllKeywords() {
        try {
            return keywordRepository.findAll();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error accessing keyword data", e);
        }
    }


    public Keyword updateKeyword(Long id, Keyword keywordDetails) {
        Keyword keyword = keywordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Keyword not found with ID: " + id));
        keyword.setName(keywordDetails.getName());
        keyword.setRecipes(keywordDetails.getRecipes());
        return keywordRepository.save(keyword);
    }

    public void deleteKeyword(Long id) {
        if (!keywordRepository.existsById(id)) {
            throw new EntityNotFoundException("No keyword found with ID: " + id);
        }
        keywordRepository.deleteById(id);
    }
}