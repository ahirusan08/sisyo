//tao
package com.example.demo.aop;

import java.time.LocalDateTime;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Rental;
import com.example.demo.repository.RentalRepository;

@Aspect
@Component
public class Checker {

	@Autowired
	RentalRepository rentalrepository;

//	Rental rental;

	@Before("execution(* com.example.demo.controller.UserAccountController.index(..))")
	public void returnBook(JoinPoint jp) {

		LocalDateTime today = LocalDateTime.now();
		
		List<Rental> list = rentalrepository.findByReturnDateIsNullAndLimitDateLessThan(today);
		System.out.println("kazu:" + list.size());
		
		for (Rental rental : list) {
			
			LocalDateTime limit = rental.getLimitDate();
			rental.setReturnDate(limit);
			
			rentalrepository.saveAndFlush(rental);
			
		}

	}
}
