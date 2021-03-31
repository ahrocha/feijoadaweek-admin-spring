package br.com.feijoadaweek.admin.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;
//
//import javax.validation.Valid;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.feijoadaweek.admin.dto.RequisicaoNovoPrato;
import br.com.feijoadaweek.admin.model.Prato;
import br.com.feijoadaweek.admin.model.Restaurante;
import br.com.feijoadaweek.admin.repository.PratoRepository;
import br.com.feijoadaweek.admin.repository.RestauranteRepository;

@Controller
public class RestauranteController {

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private PratoRepository pratoRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@RequestMapping("/restaurantes")
	public String hello(Model model) {

		System.out.println("restaurantes");

		List<Prato> pratos = pratoRepository.findTop10ByOrderByDataDesc();

		model.addAttribute("pratos", pratos);

		return "restaurantes";
	}

	@RequestMapping("/restaurante/{slug}")
	public String hello(@PathVariable("slug") String slug, @Valid RequisicaoNovoPrato requisicao, BindingResult result,
			Model model) {

		List<Restaurante> restaurantes = restauranteRepository.findBySlug(slug);
		Restaurante restaurante = restaurantes.get(0);

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			System.out.println("tem erro");
			return "restaurantes";
		}

		System.out.println("inicio insert");
		Prato prato = requisicao.toPrato();
		prato.setRestaurante(restaurante);
		System.out.println(prato.getNome());
		System.out.println("fim insert");

		List<Prato> pratos = pratoRepository.findByRestaurante(restaurante);

		model.addAttribute("restaurante", restaurante);
		model.addAttribute("pratos", pratos);

		return "restaurante";
	}
}
