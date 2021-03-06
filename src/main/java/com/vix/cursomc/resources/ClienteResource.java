package com.vix.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vix.cursomc.domain.Cliente;
import com.vix.cursomc.dto.ClienteDTO;
import com.vix.cursomc.dto.ClienteNewDTO;
import com.vix.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource
{
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id)
	{
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// MÉTODO INSERIR (POST)
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto)
	{
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// MÉTODO ALTERAR (PUT) 
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteNewDTO objDto, 
		@PathVariable Integer id)
	{
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
		
	// MÉTODO EXCLUIR (DELETE)
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
		
	// MÉTODO LISTAR (DTO)
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll()
	{
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> 
			new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
		
	// MÉTODO PARA LISTAR EM PÁGINAS
		
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage
		(@RequestParam(value = "page", defaultValue = "0")
		 Integer page,
		 @RequestParam(value = "linesPerPage", defaultValue = "24")
		 Integer linesPerPage,
		 @RequestParam(value = "direction", defaultValue = "ASC")
		 String direction,
		 @RequestParam(value = "orderBy", defaultValue = "nome")
		 String orderBy)
	{
		Page<Cliente> list = service.findPage(page, linesPerPage, direction, orderBy);		
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
