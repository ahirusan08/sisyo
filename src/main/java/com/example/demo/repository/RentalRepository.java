package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	public abstract Optional<Rental> findByBookIdAndUserId(Integer bookId, Integer userId);
	
	public abstract Optional<Rental> findByBookIdAndUserIdAndVersionNo(Integer bookId, Integer userId,Integer versionNo);
	
	public abstract Optional<Rental> findByBookIdAndVersionNo(Integer bookId, Integer versionNo);

	public abstract Optional<Rental> findBylimitDateAndUserId(LocalDate limitDate, Integer userId);
	
	
	
}
