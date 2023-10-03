package com.nam.libraryapplication.repos;

import com.nam.libraryapplication.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository <Book, Integer> {

//    boolean existsById(Book book);
}
