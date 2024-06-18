//担当：さきちゃん 
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/host")
public class HostController {

	//	@Autowired
	//	private HttpSession session;

	@GetMapping("/select")
	public String select() {

		return "select";//G202機能選択画面
	}

	@GetMapping("/rental")
	public String rentalSearch() {
		//本ID、利用者ID入力画面表示
		

		return "rentalSearch";//G211貸出返却
	}

	@GetMapping("/rental/select")
	public String rentalSelect(
			//リクエストパラメータ
			Model model,
			@RequestParam("bookId") Integer bookId,
			@RequestParam("userId") Integer userId
			) {
		//本ID、利用者IDをもとにタイトル、利用者名を検索
		//セッションスコープにsetAttributeで保存 キー名迷い中
		
		
		//↓貸出返却の選択へ
		return "rentalSelect";//G212貸出・返却選択
	}	
	
	@GetMapping("/rental/process")
	public String rentalProcess(
			//リクエストパラメータ
			Model model,
			@RequestParam("rental") Integer rental
			) {
		
		return "rentalSelect";//G212貸出・返却選択
	}

	@GetMapping("/rental/lend")
	public String lendBook() {
		//貸出処理
		return "lendBook";//G213貸出完了
	}

	@GetMapping("/rental/return")
	public String returnBook() {
		//返却処理
		return "returnBook";//G214返却完了
	}
	
	
	
	@GetMapping("/add/book")
	public String addBook() {
		//
		return "addBook";//G221
	}
	
	@PostMapping("add/book/done")
	public String addBookDone(Model model,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		//
		return "doneAddBook";//G222
	}

}
