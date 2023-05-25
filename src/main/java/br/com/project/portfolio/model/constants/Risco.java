package main.java.br.com.project.portfolio.model.constants;

public enum Risco {
    BAIXO("BAIXO"),
    MEDIO("MEDIO"),
    ALTO("ALTO");
	
	private String descricao;

	private Risco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
