package main.java.br.com.project.portfolio.frontend.beans;


import main.java.br.com.project.portfolio.model.constants.Risco;
import main.java.br.com.project.portfolio.model.constants.Status;
import java.util.Date;

public class BeansProjeto {

	private long id;
	private String nome;
	private Date datainicio;
	private Date dataprevisaofim;
	private Date datafim;
	private String descricao;
	private Status status;
	private Double orcamento;
	private Risco risco;
	
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
	public Date getDatainicio() {
		return datainicio;
	}
	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}
	public Date getDataprevisaofim() {
		return dataprevisaofim;
	}
	public void setDataprevisaofim(Date dataprevisaofim) {
		this.dataprevisaofim = dataprevisaofim;
	}
	public Date getDatafim() {
		return datafim;
	}
	public void setDatafim(Date datafim) {
		this.datafim = datafim;
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
	
}
