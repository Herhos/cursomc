package com.vix.cursomc.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vix.cursomc.domain.Cliente;
import com.vix.cursomc.domain.enums.TipoCliente;
import com.vix.cursomc.dto.ClienteNewDTO;
import com.vix.cursomc.repositories.ClienteRepository;
import com.vix.cursomc.resources.exceptions.FieldMessage;
import com.vix.cursomc.services.validations.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>
{
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context)
	{
		List<FieldMessage> list = new ArrayList<>();
		
		// PARA TESTAR A VALIDADE DO CPF
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())
			&& !BR.isValidCPF(objDto.getCpfOuCnpj()))
		{
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
		}
		
		// PARA TESTAR A VALIDADE DO CNPJ
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())
			&& !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
		{
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
		}
		
		// PARA TESTAR SE O EMAIL DO CLIENTE JÁ EXISTE
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null)
		{
			list.add(new FieldMessage("email", "E-mail já existente!"));
		}
		
		for (FieldMessage e : list)
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
