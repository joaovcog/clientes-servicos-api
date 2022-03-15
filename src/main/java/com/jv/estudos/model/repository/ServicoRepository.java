package com.jv.estudos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jv.estudos.model.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
