package com.jv.estudos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuarios")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotBlank
	private String nome;
	
	@Email
	private String email;
	
	@NotBlank
	@Column(unique = true, length = 20)
	private String usuario;
	
	@NotBlank
	@Column(length = 20)
	private String senha;
	
	private Boolean ativo;
	
	@PrePersist
	public void prePersist() {
		setAtivo(Boolean.TRUE);
	}
	
}
