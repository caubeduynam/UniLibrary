package com.nam.libraryapplication.services;

import com.nam.libraryapplication.entities.Book;
import com.nam.libraryapplication.entities.Renting;
import com.nam.libraryapplication.repos.RentingRepo;
import com.nam.libraryapplication.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepo bookRepo;

    @Autowired
    RentingRepo rentingRepo;
    public ResponseEntity<String> createBook(Book book) {
        try {
            Example<Book> bookExample = Example.of(book);
            if(bookRepo.exists(bookExample)) {
                return new ResponseEntity<>("Book existed !!!\nAdded Failed !!!", HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {
            e.getStackTrace();
        }

        bookRepo.save(book);
        return new ResponseEntity<>("Added Success !!!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> updateBookById(Integer id, Book book) {
        try {
            if(bookRepo.existsById(id)) {
                book.setBookID(id);
                bookRepo.save(book);
                return new ResponseEntity<>("Updated Success", HttpStatus.OK);
            }
        }catch(Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>("Could not find the following ID !!!\nUpdated Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteBookById(Integer id) {
        try {
            if(bookRepo.existsById(id)) {
                Book bookToDelete = bookRepo.findById(id).orElse(null);
                if(bookToDelete != null) {
                    List<Renting> rentsContainBookToDelete = rentingRepo.findRentsContainBookToDelete(bookToDelete);
                    if(!rentsContainBookToDelete.isEmpty()) {
                        return new ResponseEntity<>("You must delete rents that contain this book first !!!", HttpStatus.BAD_REQUEST);
                    } else{
                        bookRepo.deleteById(id);
                        return new ResponseEntity<>("Deleted Success !!!", HttpStatus.OK);
                    }
                }
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
            return new ResponseEntity<>("Could not find the following ID !!!\nDeleted Failed !!!", HttpStatus.BAD_REQUEST);
    }
}
