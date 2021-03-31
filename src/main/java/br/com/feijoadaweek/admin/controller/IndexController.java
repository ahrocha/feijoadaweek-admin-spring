package br.com.feijoadaweek.admin.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;
//
//import javax.validation.Valid;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.feijoadaweek.admin.repository.PratoRepository;
//import org.springframework.web.bind.annotation.RequestMethod;
import br.com.feijoadaweek.admin.model.Prato;

@Controller
public class IndexController {

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private PratoRepository pratoRepository;

	@RequestMapping("/")
	public String hello(Model model) {

		System.out.println("index");

		return "index";
	}
}
