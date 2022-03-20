package com.jv.estudos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jv.estudos.model.entity.Usuario;
import com.jv.estudos.model.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsuario(username)
					.orElseThrow(() -> new UsernameNotFoundException("Usuário inválido."));
		
		return User
				.builder()
				.username(usuario.getUsuario())
				.password(usuario.getSenha())
				.roles("USER")
				.build();
	}

}
