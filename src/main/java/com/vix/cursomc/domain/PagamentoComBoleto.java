package com.vix.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.vix.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento
{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	// CONSTRUTORES
	
	public PagamentoComBoleto() {}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,
			Date dataVencimento, Date dataPagamento)
	{
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	// GETTERS E SETTERS
	
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}	
}
