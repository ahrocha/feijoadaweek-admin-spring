package br.com.feijoadaweek.admin.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

import br.com.feijoadaweek.admin.model.Restaurante;
import br.com.feijoadaweek.admin.model.Prato;

public class RequisicaoNovoPrato {

	@NotBlank
	private String nome;

	private String status;

	@NotBlank
	private String descricao;

	private String video;

	private String data;

	private String analise;

	private String foto;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getAnalise() {
		return analise;
	}
	public void setAnalise(String analise) {
		this.analise = analise;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Prato toPrato() {
		Prato prato = new Prato();
		prato.setNome(getNome());
		prato.setFoto(getDescricao());
		prato.setFoto(getFoto());
		prato.setFoto(getVideo());
		prato.setData(LocalDateTime.now());
		prato.setStatus(1);
		return prato;
	}	
}
