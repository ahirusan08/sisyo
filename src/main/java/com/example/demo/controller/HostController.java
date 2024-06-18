//担当：さきちゃん 
package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/host")
public class HostController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	RentalRepository rentalRepository;

	@Autowired
	HttpSession session;

	LocalDateTime nowDate = LocalDateTime.now();

	@GetMapping("/select")
	public String select() {

		return "select";//G202機能選択画面
	}

	@GetMapping("/rental")
	public String rentalSearch() {
		//本ID、利用者ID入力画面表示

		return "rentalSearch";//G211貸出返却
	}

	@PostMapping("/rental/select")
	public String rentalSelect(
			//リクエストパラメータ
			Model model,
			@RequestParam(name = "bookId", required = false) Integer bookId,
			@RequestParam(name = "userId", required = false) Integer userId) {

		List<String> error = new ArrayList<>();

		//空白処理
		if (bookId == null || userId == null) {
			error.add("全項目を入力してください");
			model.addAttribute("error", error);
			return "rentalSearch";
		}
		//本ID、利用者IDをもとにタイトル、利用者名を検索

		Optional<Book> bookrecord = bookRepository.findById(bookId);
		Optional<User> userrecord = userRepository.findById(userId);

		Book book = null;
		User user = null;

		if (bookrecord.isEmpty() == false) {
			book = bookrecord.get();
		} else {
			error.add("入力した本IDは登録されていません。");
		}

		if (userrecord.isEmpty() == false) {
			user = userrecord.get();
		} else {
			error.add("入力した利用者IDは登録されていません。");
		}
		
		if(error.size() != 0  ) {
			model.addAttribute("error", error);
			return "rentalSearch";
		}
		
		//セッションスコープにsetAttributeで保存 キー名迷い中
		session.setAttribute("book", book);
		session.setAttribute("userId", userId);
		session.setAttribute("name", user.getName());

		//↓貸出返却の選択へ
		return "rentalSelect";//G212貸出・返却選択
	}

	@GetMapping("/rental/process")
	public String rentalProcess(
			Model model,
			@RequestParam("rental") Integer rental) {
		if (rental == 1) {
			return "redirect:/host/rental/lend";
		} else {
			return "redirect:/host/rental/return";
		} //G212貸出・返却選択
	}

	@GetMapping("/rental/lend")
	public String lendBook(Model model) {

		Book book = (Book) session.getAttribute("book");
		Integer userId = (Integer) session.getAttribute("userId");

		//貸出処理
		Rental rentalrecord = rentalRepository.saveAndFlush(new Rental(userId, book.getId(), 1));

		if (rentalrecord == null) {
			return "rentalSelect";
		}
		LocalDateTime time = rentalrecord.getLimitDate();
		DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		model.addAttribute("limitDate", time.format(FMT));
		return "lendBook";//G213貸出完了
	}

	@GetMapping("/rental/return")
	public String returnBook() {
		//返却処理
		Book book = (Book) session.getAttribute("book");
		Integer bookId = book.getId();
		Integer userId = (Integer) session.getAttribute("userId");

		Optional<Rental> rentalrecord = rentalRepository.findByBookIdAndUserId(bookId, userId);

		if (rentalrecord.isEmpty() == false) {
			Rental rent = rentalrecord.get();
			rent.update(1);
			rentalRepository.saveAndFlush(rent);
		} else {
			return "rentalSelect";
		}

		return "returnBook";//G214返却完了
	}

	@GetMapping("/add/book")
	public String addBook() {
		//
		return "addBook";//G221
	}

	@PostMapping("add/book/done")
	public String addBookDone(Model model,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "author", required = false) String author) {

		if (title.equals("") || author.equals("")) {
			String error = "全項目を入力してください";
			model.addAttribute("error", error);
			return "addBook";
		}

		Book book = new Book(title, author, 1);
		bookRepository.save(book);

		model.addAttribute("title", title);
		model.addAttribute("author", author);

		return "doneAddBook";//G222
	}

}
