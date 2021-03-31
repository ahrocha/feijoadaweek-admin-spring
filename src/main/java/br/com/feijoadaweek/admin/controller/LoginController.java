package br.com.feijoadaweek.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/login")
	public String login(Model model) {
		
		System.out.println("login controler");
		
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		
		System.out.println("logout controller");
		
		return "login";
	}
}



