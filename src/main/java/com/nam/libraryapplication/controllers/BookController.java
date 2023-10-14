package com.nam.libraryapplication.controllers;

import com.nam.libraryapplication.entities.Book;
import com.nam.libraryapplication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBookById(@PathVariable Integer id, @RequestBody Book book) {
        return bookService.updateBookById(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Integer id) {
        return bookService.deleteBookById(id);
    }
}
