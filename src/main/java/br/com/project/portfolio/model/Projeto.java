package main.java.br.com.project.portfolio.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.project.portfolio.model.constants.Risco;
import br.com.project.portfolio.model.constants.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import javax.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "projetos")
@Data
//@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "data_inicio")
	private LocalDateTime dataInicio;

	@Column(name = "data_previsao_fim")
	private LocalDateTime dataPrevisaoFim;

	@Column(name = "data_fim")
	private LocalDateTime dataFim;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "status")
	private Status status;

	@Column(name = "orcamento")
	private Double orcamento;

	@Column(name = "risco")
	private Risco risco;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "projetos_pessoas",
			joinColumns = { @JoinColumn(name = "projeto_id") },
			inverseJoinColumns = { @JoinColumn(name = "pessoa_id") })

	private Set<Pessoa> pessoas = new HashSet<>();

	public Projeto(){

	}
	public Projeto( String nome, LocalDateTime dataInicio, LocalDateTime dataPrevisaoFim, LocalDateTime dataFim,
					String descricao, Status status, Double orcamento, Risco risco) {

		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataPrevisaoFim = dataPrevisaoFim;
		this.dataFim = dataFim;
		this.descricao = descricao;
		this.status = status;
		this.orcamento = orcamento;
		this.risco = risco;

	}

	public void addPessoa(Pessoa pessoa) {
		this.pessoas.add(pessoa);
		pessoa.getProjetos().add(this);
	}

	public void removePessoa(long idPessoa) {
		Pessoa pessoa = this.pessoas.stream().filter(t -> t.getId() == idPessoa).findFirst().orElse(null);
		if (pessoa != null) {
			this.pessoas.remove(pessoa);
			pessoa.getProjetos().remove(this);
		}
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

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(LocalDateTime dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}

	public Risco getRisco() {
		return risco;
	}

	public void setRisco(Risco risco) {
		this.risco = risco;
	}

	public Set<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public String toString() {
		return "Projeto{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", dataInicio=" + dataInicio +
				", dataPrevisaoFim=" + dataPrevisaoFim +
				", dataFim=" + dataFim +
				", descricao='" + descricao + '\'' +
				", status=" + status +
				", orcamento=" + orcamento +
				", risco=" + risco +
				", pessoas=" + pessoas +
				'}';
	}
}
