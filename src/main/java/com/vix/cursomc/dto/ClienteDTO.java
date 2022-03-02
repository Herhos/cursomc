package com.vix.cursomc.dto;

import java.io.Serializable;

import com.vix.cursomc.domain.Cliente;
import com.vix.cursomc.services.validations.ClienteUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@ClienteUpdate
public class ClienteDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	
	private Integer id;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres!")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "E-mail inválido!")
	private String email;
	
	// CONSTRUTORES
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente obj)
	{
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
