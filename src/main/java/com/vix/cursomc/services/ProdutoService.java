package com.vix.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vix.cursomc.domain.Categoria;
import com.vix.cursomc.domain.Produto;
import com.vix.cursomc.repositories.CategoriaRepository;
import com.vix.cursomc.repositories.ProdutoRepository;
import com.vix.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService
{
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id)
	{
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException
			("Objeto não encontrado! Id: " + id + ", Tipo: " + 
			Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage,
		String direction, String orderBy)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
}
