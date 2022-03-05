package com.vix.cursomc.dto;

import java.io.Serializable;

import com.vix.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	
	private Integer id;
	private String nome;
	private Double preco;
	
	// CONSTRUTORES
	
	public ProdutoDTO() {}
	
	public ProdutoDTO(Produto obj)
	{
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}

	// GETTERS E SETTERS
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}	
}
