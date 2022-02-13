package com.vix.cursomc.domain.enums;

/* Implementação sofisticada para controle total dos
   códigos atribuídos a cada valor da enumeração.*/ 

public enum TipoCliente
{
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	// Obs: Construtor de tipo enumerado é private
	private TipoCliente(int cod, String descricao)
	{
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	// static porque a função deve ser executada mesmo 
	// sem instanciar objetos
	public static TipoCliente toEnum(Integer cod)
	{
		if (cod == null)
		{
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values())
		{
			if (cod.equals(x.getCod()))
			{
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
