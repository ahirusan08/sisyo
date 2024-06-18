//担当者:菊水

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserAccountController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAccount userAccount;
	

	@GetMapping({ "/index", "/logout" })
	public String index() {
		//セッション破棄、強制返却処理AOP？
		
		
		return "userLogin";//G101利用者ログイン画面へ
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam(name="email", required=false) String email,
			@RequestParam(name="password", required=false) String password,
			//菊：エラー文章表示用
			Model m
			) {
		
		User user = null;
		
		//エラー処理あり
		//菊：空欄処理
		if (email == null || password == null) {
			m.addAttribute("error", "メールアドレスまたはパスワードが空欄です");
			return "userLogin";
		}
		
		Optional<User> record = userRepository.findByEmailAndPassword(email, password);
		if (record.isEmpty() == true) {
			m.addAttribute("error", "メールアドレスまたはパスワードが空欄です");
			return "userLogin";
		}

		
		//正常時
		//UserAccountクラスに名前をセット(セッション管理)
		
		return "redirect:/show";//BookController 商品一覧へリダイレクト
	}
	
	@GetMapping("/add/form")
	public String addForm() {//完成！！！
		
		return "addUser";//G111利用者登録画面へ
	}
	
	@PostMapping("/add/check")
	public String addCheck(
			//@RequestParam(name="name" required=false) String name
			//Model m
			//みたいな感じ^^
			) {
		//エラー処理あり
		
		
		
		//正常時
		//m.addAttribute("name",name);
		//同様にメアド、パスワードを保持
		
		
		return "checkAddUser";//G112内容確認画面へ
	}
	
	@PostMapping("/add/done")
	public String addUser(//@RequestParam(name="name" required=false) String name
			//Model m
			//みたいな感じ^^
			) {
		//エラー処理なし
		
		//正常時
		//Userのインスタンス作成(名前、email、パスワード)
		//UserRepository.saveAndFlushで作成したインスタンスを保存
		
		//新しく作成したuserのidをゲッターで取得
		
		//m.addAttribute("name",name);
		//同様にメアド、idを保持
	
		return "doneAddUser";//登録完了画面へ
	}





}
