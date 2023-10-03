package com.nam.libraryapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Renting")
public class Renting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentID;

    @Column(name = "rentDate", nullable = false)
    private LocalDate rentDate;

    @ManyToOne
    private Student student;

    @ManyToMany
    private List<Book> book;

    public void getRentDate(Date rentDate) {
    }

    public void getBookID(List<Integer> bookID) {
    }

    public void getStudentID(Integer studentID) {
    }
}