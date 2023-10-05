package com.nam.libraryapplication.repos;

import com.nam.libraryapplication.entities.Book;
import com.nam.libraryapplication.entities.Renting;
import com.nam.libraryapplication.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RentingRepo extends JpaRepository<Renting, Integer> {
    List<Renting> findByBookIDContaining(Book bookToDelete);
    List<Renting> findAllByStudentID(Student studentToDelete);
}
