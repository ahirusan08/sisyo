package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

//	@Autowired
//	private HttpSession session;

	

	@GetMapping("/show")
	public String show() {
	
		return "showBook";
	}

	@PostMapping("/search")
	public String search(
			//
			) {
	
		return "searchBook";
	}



}
