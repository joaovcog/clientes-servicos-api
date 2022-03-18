package com.jv.estudos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jv.estudos.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
