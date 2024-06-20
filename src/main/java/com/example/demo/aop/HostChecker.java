package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.HostAccount;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class HostChecker {

	@Autowired
	HttpSession session;

	@Autowired
	HostAccount hostaccount;

	@Around("execution(!* com.example.demo.controller.HostAccountController.index(..)) || "
			+ "within(com.example.demo.controller.HostController) || "
			+ "within(com.example.demo.controller.BookController)")
	public Object HostcheckLogin(ProceedingJoinPoint jp) throws Throwable {

		Object result = jp.proceed();
		Integer id = hostaccount.getId();
		String name = hostaccount.getName();

		System.out.println("id:" + id);
		System.out.println("name:" + name);

		if (id == null || name == null) {
			System.out.println("ZZZ222");
			result = "redirect:/host/index";
		}

		return result;
	}
}
