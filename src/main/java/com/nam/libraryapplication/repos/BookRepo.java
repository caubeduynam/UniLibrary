package com.nam.libraryapplication.repos;

import com.nam.libraryapplication.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository <Book, Integer> {
}
