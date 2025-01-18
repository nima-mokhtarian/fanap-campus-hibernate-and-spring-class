package com.fanap.rest;

import com.fanap.model.Author;
import com.fanap.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


}
