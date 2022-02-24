package com.vix.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vix.cursomc.domain.Cliente;
import com.vix.cursomc.dto.ClienteDTO;
import com.vix.cursomc.repositories.ClienteRepository;
import com.vix.cursomc.services.exceptions.DataIntegrityException;
import com.vix.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService
{
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id)
	{
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException
			("Objeto não encontrado! Id: " + id + ", Tipo: " + 
			Cliente.class.getName()));
	}
	
	// MÉTODO PARA INSERIR UM CLIENTE
	
		/*public Cliente insert(Cliente obj)
		{
			obj.setId(null);
			return repo.save(obj);
		}*/
		
		// MÉTODO PARA ATUALIZAR UM CLIENTE
		
		public Cliente update(Cliente obj)
		{
			Cliente newObj = find(obj.getId());
			updateData(newObj, obj);
			return repo.save(newObj);
		}
		
		// MÉTODO PARA EXCLUIR UM CLIENTE
		
		public void delete(Integer id)
		{
			find(id);
			try
			{
				repo.deleteById(id);
			}
			catch (DataIntegrityViolationException e)
			{
				throw new DataIntegrityException("Não é possível excluir um cliente porque há entidades relacionadas!");
			}		
		}
		
		// MÉTODO PARA LISTAR TODOS OS CLIENTES
		
		public List<Cliente> findAll()
		{
			return repo.findAll();
		}
		
		// MÉTODO PARA RETORNAR CLIENTES PAGINADOS
		
		public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy)
		{
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
		}
		
		// MÉTODO AUXILIAR QUE INSTANCIA UM CLIENTE A PARTIR DE UM DTO
		
		public Cliente fromDTO(ClienteDTO objDto)
		{
			return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		}
		
		// MÉTODO AUXILIAR PARA ATUALIZAR O OBJETO RECEM CRIADO COM BASE
		// NO OBJETO QUE FOI USADO COMO ARGUMENTO
		
		private void updateData(Cliente newObj, Cliente obj)
		{
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
}
