package com.exercise.obcex789.repository;

import com.exercise.obcex789.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Long> {
}
