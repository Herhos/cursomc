package com.vix.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError
{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	// CONSTRUTORES
	
	public ValidationError(Integer status, String msg, Long timeStamp)
	{
		super(status, msg, timeStamp);		
	}
	
	// GETTERS E SETTERS

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}	
}
