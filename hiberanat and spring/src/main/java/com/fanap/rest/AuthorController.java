package com.fanap.rest;

import com.fanap.model.Author;
import com.fanap.model.Book;
import com.fanap.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> findAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/authors/search/{firstName}")
    public List<Author> findAuthorsWithFirstNameLike(@PathVariable String firstName) {
        return authorService.findAuthorsWithFirstNameLike(firstName);
    }

    @GetMapping("/authors/{id}")
    public Author finById(@PathVariable String id) {
        return authorService.findById(Long.parseLong(id));
    }


    @GetMapping("/authors/firstName/{firstName}")
    public List<Author> finByFirstName(@PathVariable String firstName) {
        return authorService.findByFirstName(firstName);
    }


    @GetMapping("/authors/lastName/{lastName}")
    public List<Author> finByLastName(@PathVariable String lastName) {
        return authorService.findByLastName(lastName);
    }

    @PostMapping("/authors")
    public void CreateAuthor(@Valid @RequestBody Author author) {
        authorService.save(author);
    }

    @PutMapping("/authors/{id}")
    public void UpdateAuthor(@PathVariable String id, @RequestBody Author author) {
        authorService.update(Long.parseLong(id), author);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable String id) {
        authorService.delete(Long.valueOf(id));
    }

    @PostMapping("/authors/{id}")
    public void addBookToAuthor(@PathVariable String id, @RequestBody Book book) {
        authorService.addBook(Long.parseLong(id), book);
    }
}
