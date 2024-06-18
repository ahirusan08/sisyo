package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	
	@Query(
			value="SELECT * FROM rentals WHERE rental_date LIKE ?",
			nativeQuery=true)
	List<Book> rental(String ym);
	
	@Query(
			value="SELECT * FROM rentals WHERE limit_date LIKE ?",
			nativeQuery=true)
	List<Book> limit(String ym);

}
