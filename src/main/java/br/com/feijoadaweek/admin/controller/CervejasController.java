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

import br.com.feijoadaweek.admin.model.Cerveja;
import br.com.feijoadaweek.admin.repository.CervejaRepository;
import br.com.feijoadaweek.admin.repository.PratoRepository;

@Controller
public class CervejasController {

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CervejaRepository cervejaRepository;

	@RequestMapping("/cervejas")
	public String hello(Model model) {

		List<Cerveja> cervejas = cervejaRepository.findTop100ByOrderByIdDesc();

		model.addAttribute("cervejas", cervejas);

		return "cervejas";
	}
}
