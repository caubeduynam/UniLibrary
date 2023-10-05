package com.nam.libraryapplication.services;

import com.nam.libraryapplication.entities.Book;
import com.nam.libraryapplication.entities.Renting;
import com.nam.libraryapplication.entities.Student;
import com.nam.libraryapplication.repos.RentingRepo;
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

    @Autowired
    RentingRepo rentingRepo;
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
        try {
            if(studentRepo.existsById(id)) {
                Student studentToDelete = studentRepo.findById(id).orElse(null);
                if(studentToDelete != null) {
                    List<Renting> rentsContainingStudent = rentingRepo.findAllByStudentID(studentToDelete);
                    if(!rentsContainingStudent.isEmpty()) {
                        return new ResponseEntity<>("You must delete rents that contain this student first !!!", HttpStatus.BAD_REQUEST);
                    } else{
                        studentRepo.deleteById(id);
                        return new ResponseEntity<>("Deleted Success !!!", HttpStatus.OK);
                    }
                }
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>("Could not find the following ID !!!\nDeleted Failed !!!", HttpStatus.BAD_REQUEST);
    }


//    public ResponseEntity<String> deleteBookById(Integer id) {
//        try {
//            if(bookRepo.existsById(id)) {
//                Book bookToDelete = bookRepo.findById(id).orElse(null);
//                if(bookToDelete != null) {
//                    List<Renting> rentsContainingBook = rentingRepo.findByBookIDContaining(bookToDelete);
//                    if(!rentsContainingBook.isEmpty()) {
//                        return new ResponseEntity<>("You must delete rents that contain this book first !!!", HttpStatus.BAD_REQUEST);
//                    } else{
//                        bookRepo.deleteById(id);
//                        return new ResponseEntity<>("Deleted Success !!!", HttpStatus.OK);
//                    }
//                }
//            }
//        } catch(Exception e) {
//            e.getStackTrace();
//        }
//        return new ResponseEntity<>("Could not find the following ID !!!\nDeleted Failed !!!", HttpStatus.BAD_REQUEST);
//    }
}
