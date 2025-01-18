package com.fanap.service;

import com.fanap.dataaccess.AuthorRepository;
import com.fanap.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("from Author", Author.class)
               .getResultList();
    }

    @Override
    public List<Author> findAuthorsWithFirstNameLike(String firstName) {
        return authorRepository.findAuthorsByFirstNameStartingWith(firstName);
    }

    @Override
    public List<Author> findAll(int from, int size) {
        return entityManager.createQuery("from Author", Author.class)
                .setFirstResult(from)
                .setMaxResults(size).getResultList();
    }


    @Override
    public List<String> findAllHavingMoreThanOneBook() {
        return null;
    }

    @Override
    public List<String> findAllHavingAtMostOneBook() {
        return null;
    }

    @Override
    public Author findById(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        }
        throw new EmployeeNotFoundException("no employee found with given identifier. id = " + id);
    }

    @Override
    public List<Author> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Author> findByLastName(String lastName) {
        return null;
    }

    @Override
    public void save(Author author) {
    }

    @Override
    public void update(Author author) {
    }

    @Override
    public void delete(Author author) {
    }


}
