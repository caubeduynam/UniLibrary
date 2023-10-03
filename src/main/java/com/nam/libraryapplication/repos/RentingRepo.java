package com.nam.libraryapplication.repos;

import com.nam.libraryapplication.entities.Renting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentingRepo extends JpaRepository<Renting, Integer> {
}
