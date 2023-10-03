package com.nam.libraryapplication.services;

import com.nam.libraryapplication.entities.Renting;
import com.nam.libraryapplication.repos.BookRepo;
import com.nam.libraryapplication.repos.RentingRepo;
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

    public ResponseEntity<String> createRenting(Date rentDate, Integer studentID, List<Integer> bookID, Renting renting) {
        Integer temp = 0;
        try {
            for(int i = 0; i < bookID.size(); i++) {
                if(!bookRepo.existsById(bookID.get(i))) {
                    temp++;
                }
            }
            if(temp != 0) {
                return new ResponseEntity<>("Book not existed !!! \nCreated Failed !!!" , HttpStatus.OK);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        if(temp == 0) {
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
}
