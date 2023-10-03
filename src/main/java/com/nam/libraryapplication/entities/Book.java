package com.nam.libraryapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;

    @Column(name = "BookName", nullable = false)
    private String bookName;

    @Column(name = "Author", nullable = false)
    private String author;

    @Column(name = "Price", nullable = false)
    private Float price;
}
