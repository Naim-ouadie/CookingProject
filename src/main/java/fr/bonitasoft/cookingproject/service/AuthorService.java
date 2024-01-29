package fr.bonitasoft.cookingproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import fr.bonitasoft.cookingproject.entities.Author;
import fr.bonitasoft.cookingproject.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        return authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));
    }

    public List<Author> getAllAuthors() {
        try {
            return authorRepository.findAll();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error accessing author data", e);
        }
    }


    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));
        author.setAuthorName(authorDetails.getAuthorName());
        author.setBio(authorDetails.getBio());
        author.setRecipes(authorDetails.getRecipes());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("No author found with ID: " + id);
        }
        authorRepository.deleteById(id);
    }
}
