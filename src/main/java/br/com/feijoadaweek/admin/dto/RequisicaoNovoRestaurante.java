package br.com.feijoadaweek.admin.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

import br.com.feijoadaweek.admin.model.Restaurante;

public class RequisicaoNovoRestaurante {

	@NotBlank
	private String nome;

	@NotBlank
	private String slug;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Restaurante toRestaurante() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(getNome());
		restaurante.setSlug(getSlug());
		restaurante.setData(LocalDateTime.now());
		restaurante.setStatus(1);
		return restaurante;
	}	
}
