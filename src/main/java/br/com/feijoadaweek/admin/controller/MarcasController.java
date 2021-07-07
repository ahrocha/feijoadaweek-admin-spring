package br.com.feijoadaweek.admin.controller;

import java.util.List;

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

import br.com.feijoadaweek.admin.model.Marca;
import br.com.feijoadaweek.admin.repository.MarcaRepository;

@Controller
public class MarcasController {

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private MarcaRepository marcaRepository;

	@RequestMapping("/marcas")
	public String hello(Model model) {

		List<Marca> marcas = marcaRepository.findTop100ByOrderByIdDesc();

		model.addAttribute("marcas", marcas);

		return "marcas";
	}
}
