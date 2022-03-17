package com.jv.estudos.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jv.estudos.model.entity.Cliente;
import com.jv.estudos.model.entity.ServicoPrestado;
import com.jv.estudos.model.repository.ClienteRepository;
import com.jv.estudos.model.repository.ServicoPrestadoRepository;
import com.jv.estudos.util.BigDecimalConverter;

@RestController
@RequestMapping("/servicos-prestados")
public class ServicoPrestadoController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ServicoPrestadoRepository servicoPrestadoRepository;
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody com.jv.estudos.model.dto.ServicoPrestadoDTO servicoPrestadoDto) {
		Optional<Cliente> optCliente = clienteRepository.findById(servicoPrestadoDto.getIdCliente());
		Cliente cliente = optCliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(servicoPrestadoDto.getDescricao());
		servicoPrestado.setData(LocalDate.parse(servicoPrestadoDto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(servicoPrestadoDto.getValor()));
		
		return servicoPrestadoRepository.save(servicoPrestado);
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome, 
			@RequestParam(value = "mes", required = false) Integer mes) {
		return servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}
	
}
