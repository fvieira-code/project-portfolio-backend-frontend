package main.java.br.com.project.portfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pessoas")
@Data
//@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "datanascimento")
	private Date dataNascimento;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "funcionario")
	private Boolean funcionario;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "pessoas")
	@JsonIgnore
	private Set<Projeto> projetos = new HashSet<>();

	public Set<Projeto> getProjetos() {
		return projetos;
	}

	public Pessoa(){

	}
	public Pessoa(String nome, Date dataNascimento, String cpf, Boolean funcionario) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.funcionario = funcionario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Boolean funcionario) {
		this.funcionario = funcionario;
	}

	public void setProjetos(Set<Projeto> projetos) {
		this.projetos = projetos;
	}
}
