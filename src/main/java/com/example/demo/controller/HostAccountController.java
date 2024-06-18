//担当：たおちゃん
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/host")
public class HostAccountController {

	@Autowired
	private HttpSession session;

	@GetMapping({ "/index", "/logout" })
	public String index() {
		//セッション破棄、強制返却処理AOP？
		session.invalidate();
		

		return "hostLogin";
	}

	@GetMapping("/login")
	public String login(
	//@RequestParam(name="id" required=false) String id

	) {
		//エラー処理あり
		//aaa

		//正常時

		//HostAccountクラスに名前をセット(セッション管理)

		return "select";//G202機能選択画面へ
	}

}
