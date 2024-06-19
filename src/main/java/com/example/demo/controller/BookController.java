package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.Rental;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalRepository;

@Controller
public class BookController {

	//	@Autowired
	//	private HttpSession session;
	
	@Autowired
	BookRepository bookRepository;

	@Autowired
	RentalRepository rentalRepository;

	@GetMapping("/show")
	public String show(Model m) {
		List<Book> books = bookRepository.findAll();
		m.addAttribute("books", books);

		return "showBook";
	}

	@PostMapping("/search")
	public String search(
			@RequestParam(name = "ym", required = false) String ym,
			Model m) {
		
		//検索データ保持
		m.addAttribute("ym", ym);
		
		//全件検索
		List<Book> books = bookRepository.findAll();
		m.addAttribute("books", books);
		
		//その月は何日まで？
		String day[]=ym.split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(day[0]), Integer.parseInt(day[1]), 1);
		int maxDay = date.lengthOfMonth();
		m.addAttribute("maxDay", maxDay);
		
		//貸出状況表示
		
		//①貸出日または返却期限日がymであるものを検索
		List<Rental> rentals=rentalRepository.rental(ym+"%",ym+"%");
		
		
		//②未返却の本の返却日を、返却期限日で仮置きする
		for(Rental rental :rentals) {
			if(rental.getReturnDate()==null) {
				
				rental.setReturnDate(rental.getLimitDate());
				
			}
			
		}
		
		
		m.addAttribute("rentals", rentals);
		
		return "searchBook";
	}
	
	

}
