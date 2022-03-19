package com.jv.estudos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jv.estudos.model.entity.Usuario;
import com.jv.estudos.model.repository.UsuarioRepository;
import com.jv.estudos.service.exception.UsuarioCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario) {
		boolean exists = usuarioRepository.existsByUsuario(usuario.getUsuario());
		
		if (exists) {
			throw new UsuarioCadastradoException("Usuário já existe");
		}
		
		this.usuarioRepository.save(usuario);
	}
	
}
