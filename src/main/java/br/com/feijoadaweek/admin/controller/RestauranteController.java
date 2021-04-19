package br.com.feijoadaweek.admin.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.feijoadaweek.admin.dto.RequisicaoNovoPrato;
import br.com.feijoadaweek.admin.dto.RequisicaoNovoRestaurante;
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

	@GetMapping("/restaurantes")
	public String list(Model model) {

		List<Restaurante> restaurantes = restauranteRepository.findTop100ByOrderByDataDesc();

		model.addAttribute("restaurantes", restaurantes);

		return "restaurantes";
	}

	@PostMapping("/restaurantes")
	public String create(Model model, @Valid RequisicaoNovoRestaurante requisicao, BindingResult result) {

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			System.out.println("tem erro");
			return this.list(model);
		}

		Restaurante restaurante = requisicao.toRestaurante();

		// @TODO validar slug repetido
		restauranteRepository.save(restaurante);

		return this.list(model);
	}

	@GetMapping("/restaurante/{slug}")
	public String show(@PathVariable("slug") String slug, RequisicaoNovoPrato requisicao, Model model) {

		List<Restaurante> restaurantes = restauranteRepository.findBySlug(slug);
		Restaurante restaurante = restaurantes.get(0);

		List<Prato> pratos = pratoRepository.findByRestaurante(restaurante);

		model.addAttribute("restaurante", restaurante);
		model.addAttribute("pratos", pratos);

		return "restaurante";
	}

	@PostMapping("/restaurante/{slug}")
	public String save(@PathVariable("slug") String slug, @Valid RequisicaoNovoPrato requisicao, BindingResult result,
			Model model) {

		List<Restaurante> restaurantes = restauranteRepository.findBySlug(slug);
		Restaurante restaurante = restaurantes.get(0);

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}

			List<Prato> pratos = pratoRepository.findByRestaurante(restaurante);

			model.addAttribute("restaurante", restaurante);
			model.addAttribute("pratos", pratos);

			return "restaurante";
		}

		System.out.println("inicio insert");
		Prato prato = requisicao.toPrato();
		prato.setRestaurante(restaurante);

		System.out.println("vai gravar");
		pratoRepository.save(prato);
		System.out.println("acabou de gravar");
		//restauranteRepository.save(restaurante);

		List<Prato> pratos = pratoRepository.findByRestaurante(restaurante);

		// @todo se gravar com sucesso, limpar o formulário
		// @todo exibir mensagem de sucesso
		model.addAttribute("restaurante", restaurante);
		model.addAttribute("pratos", pratos);

		return "restaurante";
	}
}
