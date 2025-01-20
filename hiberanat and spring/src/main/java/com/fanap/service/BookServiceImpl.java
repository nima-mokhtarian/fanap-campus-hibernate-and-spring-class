package com.fanap.service;


import com.fanap.dataaccess.BookRepository;
import com.fanap.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return  null;
    }

    @Override
    public Book getById(long id) {
        return  null;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Book book) {
    }

    @Override
    public void delete(Book book) {
    }
}
