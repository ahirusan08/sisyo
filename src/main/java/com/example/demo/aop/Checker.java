//tao
package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Rental;

@Aspect
@Component
public class Checker {

	Rental rental;

	@Before("execution(* com.example.demo.controller.UserController.index.*(..))")
	public void returnBook(JoinPoint jp) {
		
		
		
		

	}
}
