package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	public abstract Optional<Rental> findByBookIdAndUserId(Integer bookId, Integer userId);
}
