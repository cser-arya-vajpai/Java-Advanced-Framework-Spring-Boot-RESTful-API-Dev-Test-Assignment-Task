package com.capgemini.controller;

import com.capgemini.model.Book;
import com.capgemini.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public Object addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/{id}")
    public Object getBook(@PathVariable int id) {
        return service.getBook(id);
    }

    @GetMapping
    public Object getAllBooks() {
        return service.getAllBooks();
    }

    @PutMapping("/{id}")
    public Object updateBook(@PathVariable int id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public Object deleteBook(@PathVariable int id) {
        return service.deleteBook(id);
    }

    @PostMapping("/borrow/{id}")
    public Object borrowBook(@PathVariable int id) {
        return service.borrowBook(id);
    }

    @PostMapping("/return/{id}")
    public Object returnBook(@PathVariable int id) {
        return service.returnBook(id);
    }
}