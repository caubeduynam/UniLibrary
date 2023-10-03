package com.nam.libraryapplication.services;

import com.nam.libraryapplication.entities.Student;
import com.nam.libraryapplication.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    public ResponseEntity<String> createStudent(Student student) {
        try {
            Example<Student> studentExample = Example.of(student);
            if(studentRepo.exists(studentExample)) {
                return new ResponseEntity<>("Student exsited !!!\nAdded Failed !!!", HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {
            e.getStackTrace();
        }

        studentRepo.save(student);
        return new ResponseEntity<>("Added Success !!!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Student>> getStudent() {
        return new ResponseEntity<>(studentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> updateStudentById(Integer id, Student student) {
        try {
            if(studentRepo.existsById(id)) {
                student.setStudentID(id);
                studentRepo.save(student);
                return new ResponseEntity<>("Updated Success !!!", HttpStatus.OK);
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>("Could not find the following ID !!!\nUpdated Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteStudentById(Integer id) {
        if(studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find the following ID !!!\nDeleted Failed !!!", HttpStatus.BAD_REQUEST);
        }
    }
}
