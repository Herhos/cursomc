package com.vix.cursomc.dto;

import java.io.Serializable;

import com.vix.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS	
	
	private Integer id;
	private String nome;
		
	// CONTRUTORES
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj)
	{
		id = obj.getId();
		nome = obj.getNome();
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
}
