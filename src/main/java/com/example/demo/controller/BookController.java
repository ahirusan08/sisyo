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

		//年月ymの型変換
		String yearMonth[] = ym.split("-");
		Integer year = Integer.parseInt(yearMonth[0]);
		Integer month = Integer.parseInt(yearMonth[1]);
		m.addAttribute("year", year);
		m.addAttribute("month", month);

		//その月は何日まで？
		LocalDate date = LocalDate.of(year, month, 1);
		int maxDay = date.lengthOfMonth();
		m.addAttribute("maxDay", maxDay);

		//貸出状況表示
		//①本は全部で何冊？
		Integer allBooks = books.size();

		//②-1 貸出状況を管理する多次元配列bookRentalを作成
		Integer[][] rentalBook = new Integer[allBooks][31];

		//②-2 bookRental初期化
		for (int i = 0; i < allBooks; i++) {//すべての本の
			for (int j = 0; j < 31; j++) {//全日程において
				rentalBook[i][j] = 0;//貸出状況を返却状態(0)に初期化
			}
		}

		List<Rental> rentals = null;

		//③貸出状況のセット
		for (int i = 0; i < allBooks; i++) {//すべての本に対して
			rentals = rentalRepository.findByBookId(i+1);//bookIdの一致するrentalsを検索
			for (int j = 0; j < 31; j++) {//すべての日程に対して
				for (Rental r : rentals) {//rentalsに対して
					
					//③-0	 まだ返却されていない場合
					if(r.getReturnDate()==null) {
						r.setReturnDate(r.getLimitDate());//返却日に返却期限日を仮set
					}
					//③-1	 月を跨がない予約(貸出日と返却期限日が今月)
					if (r.getRentalDate().getMonthValue() == month && r.getReturnDate().getMonthValue() == month) {
						if (r.getRentalDate().getDayOfMonth() <= j+1 && r.getReturnDate().getDayOfMonth() >= j+1) {
							rentalBook[i][j] = 1;
						}
					}

					//③-2	 月を跨ぐ予約
					//③-2-1 貸出日は今月だが返却日が来月のとき
					if (r.getRentalDate().getMonthValue() == month &&r.getReturnDate().getMonthValue() == month + 1) {
						if (r.getRentalDate().getDayOfMonth() <= j+1) {
							rentalBook[i][j] = 1;
						}
					}
					//③-2-2 貸出日は先月だが返却日が今月のとき
					if (r.getRentalDate().getMonthValue() == month - 1 && r.getReturnDate().getMonthValue() == month) {
						if (r.getReturnDate().getDayOfMonth() >= j+1) {
							rentalBook[i][j] = 1;
						}
					}

				}
			}
		}
		
		//④データをHTMLへ
		m.addAttribute("rentalBook", rentalBook);
		
		
		return "searchBook";
		
	}

}
