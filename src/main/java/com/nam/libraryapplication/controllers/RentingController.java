package com.nam.libraryapplication.controllers;

import com.nam.libraryapplication.entities.Renting;
import com.nam.libraryapplication.services.RentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/renting")
public class RentingController {

    @Autowired
    RentingService rentingService;

    @PostMapping("/create")
    public ResponseEntity<String> createRenting(@RequestParam Date rentDate, @RequestParam Integer studentID,
                                                @RequestParam List<Integer> bookID, Renting renting) {
        return rentingService.createRenting(rentDate, studentID, bookID, renting);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Renting>> getRenting() {
        return rentingService.getRenting();
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateRentingByID(@PathVariable Integer id, @RequestBody Renting renting) {
//        return rentingService.updateRentingByID(id, renting);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRentingByID(@PathVariable Integer id) {
        return rentingService.deleteRentingByID(id);
    }
}
