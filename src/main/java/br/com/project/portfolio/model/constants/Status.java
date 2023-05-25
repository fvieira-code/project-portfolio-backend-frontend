package main.java.br.com.project.portfolio.model.constants;

public enum Status {
    EM_ANALISE("AN"),
    ANALISE_REALIZADA("AR"),
    ANALISE_APROVADA("AA"),
    INICIADO("IN"),
    PLANEJADO("PL"),
    EM_ANADAMENTO("EA"),
    ENCERRADO("EN"),
    CANCELADO("CN");
	
	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
