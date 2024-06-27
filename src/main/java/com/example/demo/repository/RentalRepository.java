package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	List<Rental> findByBookId(Integer bookId);

	Optional<Rental> findByBookIdAndUserId(Integer bookId, Integer userId);

	Optional<Rental> findByBookIdAndUserIdAndVersionNo(Integer bookId, Integer userId, Integer versionNo);

	Optional<Rental> findByBookIdAndVersionNo(Integer bookId, Integer versionNo);

	Optional<Rental> findBylimitDateAndUserId(LocalDate limitDate, Integer userId);

	List<Rental> findByReturnDateIsNullAndVersionNoLessThanAndLimitDateLessThan(Integer no, LocalDateTime today);



}
