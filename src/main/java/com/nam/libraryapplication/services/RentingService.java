package com.nam.libraryapplication.services;

import com.nam.libraryapplication.entities.Renting;
import com.nam.libraryapplication.repos.BookRepo;
import com.nam.libraryapplication.repos.RentingRepo;
import com.nam.libraryapplication.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RentingService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    RentingRepo rentingRepo;

    @Autowired
    StudentRepo studentRepo;

    public ResponseEntity<String> createRenting(Date rentDate, Integer studentID, List<Integer> bookID, Renting renting) {
        Integer temp = 0;
        Integer temp_temp = 0;
        try {
            for(int i = 0; i < bookID.size(); i++) {
                if(!bookRepo.existsById(bookID.get(i))) {
                    temp++;
                }
            }
            if(!studentRepo.existsById(studentID)) {
                temp_temp++;
            }
            if(temp != 0 || temp_temp != 0) {
                return new ResponseEntity<>("Book or Student not existed !!! \nCreated Failed !!!" , HttpStatus.OK);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        if(temp == 0 && temp_temp == 0) {
            renting.getRentDate(rentDate);
            renting.getBookID(bookID);
            renting.getStudentID(studentID);
            rentingRepo.save(renting);
            return new ResponseEntity<>("Created Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Created Failed", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Renting>> getRenting() {
        return new ResponseEntity<>(rentingRepo.findAll(), HttpStatus.OK);
    }

// ******BUGGED
    public ResponseEntity<String> updateRentingByID(Integer id, Renting renting) {
        Integer temp = 0;
        Integer temp_temp = 0;
        try {
            if(rentingRepo.existsById(id)) {
                for(int i = 0; i < renting.getBookID().size(); i++) {
                    if(!bookRepo.existsById(renting.getBookID().get(i).getBookID())) {
                        temp++;
                    }
                }
                if(!studentRepo.existsById(renting.getStudentID().getStudentID())) {
                    temp_temp++;
                }
                if(temp != 0 || temp_temp != 0) {
                    return new ResponseEntity<>("Book or Student not existed !!! \nUpdated Failed !!!" , HttpStatus.OK);
                } else {
                    renting.setRentID(id);
                    rentingRepo.save(renting);
                    return new ResponseEntity<>("Updated Success", HttpStatus.OK);
                }
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>("Could not find the following ID !!!\nUpdated Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteRentingByID(Integer id) {
        if (rentingRepo.existsById(id)) {
            rentingRepo.deleteById(id);
            return new ResponseEntity<>("Deleted Success !!!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find the following ID !!!\nDeleted Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
