package com.nam.libraryapplication.repos;

import com.nam.libraryapplication.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
