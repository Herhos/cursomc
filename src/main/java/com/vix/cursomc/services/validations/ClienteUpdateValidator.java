package com.vix.cursomc.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.vix.cursomc.domain.Cliente;
import com.vix.cursomc.dto.ClienteDTO;
import com.vix.cursomc.repositories.ClienteRepository;
import com.vix.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context)
	{
		@SuppressWarnings("unchecked")
		Map<String, String> mapa = (Map<String, String>) request.getAttribute
			(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(mapa.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// PARA TESTAR SE O EMAIL DO CLIENTE JÁ EXISTE E
		// SE O ID É DO MESMO CLIENTE QUE SERÁ ATUALIZADO
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId))
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
