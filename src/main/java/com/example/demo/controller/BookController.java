package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

//	@Autowired
//	private HttpSession session;

	

	@GetMapping("/show")
	public String show() {
	
		return "showBook";
	}

	@GetMapping("/search")
	public String search() {
	
		return "searchBook";
	}



}
