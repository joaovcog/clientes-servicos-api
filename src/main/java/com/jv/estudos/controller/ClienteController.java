package com.jv.estudos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jv.estudos.model.entity.Cliente;
import com.jv.estudos.model.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cliente buscarPorId(@PathVariable Integer id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public Cliente atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
		Optional<Cliente> optClienteExistente = clienteRepository.findById(id);
		
		if (optClienteExistente.isPresent()) {
			Cliente clienteExistente = optClienteExistente.get();
			
			BeanUtils.copyProperties(clienteAtualizado, clienteExistente, "id", "dataCadastro");
			return clienteRepository.save(clienteExistente);
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
		
//		clienteRepository
//			.findById(id)
//			.map(cliente -> {
//				BeanUtils.copyProperties(clienteAtualizado, cliente, "id", "dataCadastro");
//				
//				return clienteRepository.save(cliente);
//			})
//			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
		}
//		clienteRepository.findById(id).map(cliente -> {
//			clienteRepository.delete(cliente);
//			return Void.TYPE;
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
