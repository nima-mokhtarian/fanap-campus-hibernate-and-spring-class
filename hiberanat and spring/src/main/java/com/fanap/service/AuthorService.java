package com.fanap.service;


import com.fanap.model.Author;
import com.fanap.model.Book;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    List<Author> findAuthorsWithFirstNameLike(String firstName);

    List<Author> findAll(int from, int size);

    List<String> findAllHavingMoreThanOneBook();

    List<String> findAllHavingAtMostOneBook();

    Author findById(long id);

    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    void save(Author author);

    void update(long id, Author author);

    void delete(Long id);

    void addBook(long id, Book book);
}
