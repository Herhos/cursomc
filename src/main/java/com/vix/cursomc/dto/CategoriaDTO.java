package com.vix.cursomc.dto;

import java.io.Serializable;

// Para esse import acrescentar a dependency no pom.xml
import javax.validation.constraints.NotEmpty;
// Para esse import acrescentar a dependency no pom.xml
import org.hibernate.validator.constraints.Length;

import com.vix.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS	
	
	private Integer id;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min =5, max = 80, message ="O tamanho dever ser entre 5 e 80 caracteres!")
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
