package fr.bonitasoft.cookingproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ingredient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<Recipe> recipes;
}
