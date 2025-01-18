package com.fanap;

import com.fanap.service.AuthorService;
import com.fanap.service.AuthorServiceImpl;
import com.fanap.service.BookService;
import com.fanap.service.BookServiceImpl;

import java.util.List;

public class Main {

    static BookService bookService = new BookServiceImpl();
    static AuthorService authorService = new AuthorServiceImpl();

    public static void main(String[] args) {
        List<String> authorList = authorService.findAllHavingAtMostOneBook();
        authorList.forEach(System.out::println);
    }
}