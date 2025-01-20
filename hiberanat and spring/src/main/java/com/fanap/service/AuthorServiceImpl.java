package com.fanap.service;

import com.fanap.dataaccess.AuthorRepository;
import com.fanap.model.Author;
import com.fanap.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAuthorsWithFirstNameLike(String firstName) {
        return authorRepository.findAuthorsByFirstNameStartingWith(firstName);
    }

    @Override
    public List<Author> findAll(int from, int size) {
        Page<Author> authorList = authorRepository.findAll(PageRequest.of(from / size, size));
        return authorList.stream().collect(Collectors.toList());
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
        return authorRepository.findByFirstName(firstName);
    }

    @Override
    public List<Author> findByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void update(long id, Author author) {
        authorRepository.findById(id)
                .ifPresentOrElse(au -> {
                            author.setId(id);
                            authorRepository.save(author);
                        },
                        () -> {
                            throw new EmployeeNotFoundException("no employee found with given identifier. id = " + id);
                        });
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void addBook(long id, Book book) {
        authorRepository.findById(id)
                .ifPresentOrElse(au -> {
                            book.setAuthor(au);
                            au.getBooks().add(book);
                            authorRepository.save(au);
                        },
                        () -> {
                            throw new EmployeeNotFoundException("no employee found with given identifier. id = " + id);
                        });
    }
}