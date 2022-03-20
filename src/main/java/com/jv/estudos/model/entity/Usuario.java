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
	
	@NotBlank(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@Email(message = "{campo.email.invalido}")
	@NotBlank(message = "{campo.email.obrigatorio}")
	private String email;
	
	@NotBlank(message = "{campo.usuario.obrigatorio}")
	@Column(unique = true, length = 20)
	private String usuario;
	
	@NotBlank(message = "{campo.senha.obrigatorio}")
	private String senha;
	
	private Boolean ativo;
	
	@PrePersist
	public void prePersist() {
		setAtivo(Boolean.TRUE);
	}
	
}
