package com.vix.cursomc.domain;

import javax.persistence.Entity;

import com.vix.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento
{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	
	private Integer numeroDeParcelas;
	
	// CONSTRUTORES
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas)
	{
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	// GETTERS E SETTERS
	
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}	
}
