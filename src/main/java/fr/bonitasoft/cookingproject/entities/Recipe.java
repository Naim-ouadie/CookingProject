package fr.bonitasoft.cookingproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToMany
    private List<Ingredient> ingredients;
    @ManyToMany
    private List<Keyword> keywords;
    @ManyToOne
    private Author author;
}
