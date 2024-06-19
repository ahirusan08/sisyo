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

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class Checker {

	@Autowired
	RentalRepository rentalrepository;

	@Autowired
	HttpSession session;

	//	Rental rental;

	@Before("execution(* com.example.demo.controller.UserAccountController.index(..))")
	public void returnBook(JoinPoint jp) {

		LocalDateTime today = LocalDateTime.now();

		List<Rental> list = rentalrepository.findByReturnDateIsNullAndVersionNoLessThanAndLimitDateLessThan(2, today);

		for (Rental rental : list) {

			LocalDateTime limit = rental.getLimitDate();
			rental.setReturnDate(limit);

			Integer vnum = rental.getVersionNo();

			if (vnum == 1) {
				rental.setVersionNo(2);
			}

			rentalrepository.saveAndFlush(rental);

			session.invalidate();
		}

	}

	@Before("execution(* com.example.demo.controller.HostAccountController.index(..))")
	public void trushCan(JoinPoint jp) {
		
		session.invalidate();

	}
}
