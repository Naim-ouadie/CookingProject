package fr.bonitasoft.cookingproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.bonitasoft.cookingproject.entities.Keyword;
import fr.bonitasoft.cookingproject.service.KeywordService;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    @Autowired
    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @PostMapping("/")
    public ResponseEntity<Keyword> createKeyword(@RequestBody Keyword keyword) {
        Keyword createdKeyword = keywordService.createKeyword(keyword);
        return ResponseEntity.ok(createdKeyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Keyword> getKeywordById(@PathVariable Long id) {
        Keyword keyword = keywordService.getKeywordById(id);
        return ResponseEntity.ok(keyword);
    }

    @GetMapping("/")
    public ResponseEntity<List<Keyword>> getAllKeywords() {
        List<Keyword> keywords = keywordService.getAllKeywords();
        return ResponseEntity.ok(keywords);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Keyword> updateKeyword(@PathVariable Long id, @RequestBody Keyword keywordDetails) {
        Keyword updatedKeyword = keywordService.updateKeyword(id, keywordDetails);
        return ResponseEntity.ok(updatedKeyword);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKeyword(@PathVariable Long id) {
        keywordService.deleteKeyword(id);
        return ResponseEntity.ok().build();
    }
}
