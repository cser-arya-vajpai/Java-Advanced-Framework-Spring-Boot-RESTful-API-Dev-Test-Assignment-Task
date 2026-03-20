package com.capgemini.services;

import com.capgemini.model.Book;
import com.capgemini.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    // CREATE
    public Map<String, Object> addBook(Book book) {
        Map<String, Object> response = new HashMap<>();

        if (book.getAvailableCopies() == null || book.getAvailableCopies() < 0) {
            response.put("message", "Available copies cannot be negative");
            return response;
        }

        book.setBorrowedCopies(0);

        Book saved = repository.save(book);

        response.put("message", "Book added successfully");
        response.put("id", saved.getId());

        return response;
    }

    // READ ONE
    public Object getBook(int id) {
        Optional<Book> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return "Book not found";
    }

    // READ ALL
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // UPDATE
    public Map<String, String> updateBook(int id, Book updated) {
        Map<String, String> response = new HashMap<>();

        Optional<Book> optional = repository.findById(id);

        if (optional.isEmpty()) {
            response.put("message", "Book not found");
            return response;
        }

        if (updated.getAvailableCopies() == null || updated.getAvailableCopies() < 0) {
            response.put("message", "Available copies cannot be negative");
            return response;
        }

        Book existing = optional.get();

        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        existing.setAvailableCopies(updated.getAvailableCopies());

        repository.save(existing);

        response.put("message", "Book updated successfully");
        return response;
    }

    // DELETE
    public Map<String, String> deleteBook(int id) {
        Map<String, String> response = new HashMap<>();

        if (!repository.existsById(id)) {
            response.put("message", "Book not found");
            return response;
        }

        repository.deleteById(id);

        response.put("message", "Book deleted successfully");
        return response;
    }

    // BORROW
    public Map<String, String> borrowBook(int id) {
        Map<String, String> response = new HashMap<>();

        Optional<Book> optional = repository.findById(id);

        if (optional.isEmpty()) {
            response.put("message", "Book not found");
            return response;
        }

        Book book = optional.get();

        if (book.getAvailableCopies() <= 0) {
            response.put("message", "No copies available");
            return response;
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        book.setBorrowedCopies(book.getBorrowedCopies() + 1);

        repository.save(book);

        response.put("message", "Book borrowed successfully");
        return response;
    }

    // RETURN
    public Map<String, String> returnBook(int id) {
        Map<String, String> response = new HashMap<>();

        Optional<Book> optional = repository.findById(id);

        if (optional.isEmpty()) {
            response.put("message", "Book not found");
            return response;
        }

        Book book = optional.get();

        if (book.getBorrowedCopies() <= 0) {
            response.put("message", "No borrowed books to return");
            return response;
        }

        book.setBorrowedCopies(book.getBorrowedCopies() - 1);
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        repository.save(book);

        response.put("message", "Book returned successfully");
        return response;
    }
}