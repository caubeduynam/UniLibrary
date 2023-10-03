package com.nam.libraryapplication.controllers;

import com.nam.libraryapplication.entities.Student;
import com.nam.libraryapplication.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudent() {
        return studentService.getStudent();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.updateStudentById(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
        return studentService.deleteStudentById(id);
    }
}
