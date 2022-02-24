package com.vix.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vix.cursomc.domain.Categoria;
import com.vix.cursomc.dto.CategoriaDTO;
import com.vix.cursomc.repositories.CategoriaRepository;
import com.vix.cursomc.services.exceptions.DataIntegrityException;
import com.vix.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService
{
	@Autowired
	private CategoriaRepository repo;
	
	// MÉTODO PARA BUSCAR UMA CATEGORIA POR ID
	
	public Categoria find(Integer id)
	{
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException
			("Objeto não encontrado! Id: " + id + ", Tipo: " + 
			Categoria.class.getName()));
	}
	
	// MÉTODO PARA INSERIR UMA CATEGORIA
	
	public Categoria insert(Categoria obj)
	{
		obj.setId(null);
		return repo.save(obj);
	}
	
	// MÉTODO PARA ATUALIZAR UMA CATEGORIA
	
	public Categoria update(Categoria obj)
	{
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	// MÉTODO PARA EXCLUIR UMA CATEGORIA
	
	public void delete(Integer id)
	{
		find(id);
		try
		{
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}		
	}
	
	// MÉTODO PARA LISTAR TODAS AS CATEGORIAS
	
	public List<Categoria> findAll()
	{
		return repo.findAll();
	}
	
	// MÉTODO PARA RETORNAR CATEGORIAS PAGINADAS
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	// MÉTODO AUXILIAR QUE INSTANCIA UMA CATEGORIA A PARTIR DE UM DTO
	
	public Categoria fromDTO(CategoriaDTO objDto)
	{
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	// MÉTODO AUXILIAR PARA ATUALIZAR O OBJETO RECEM CRIADO COM BASE
	// NO OBJETO QUE FOI USADO COMO ARGUMENTO
			
	private void updateData(Categoria newObj, Categoria obj)
	{
		newObj.setNome(obj.getNome());
	}
}
