package com.jv.estudos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jv.estudos.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
