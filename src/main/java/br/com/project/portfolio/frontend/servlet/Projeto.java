package main.java.br.com.project.portfolio.frontend.servlet;

import main.java.br.com.project.portfolio.frontend.beans.BeansProjeto;
import main.java.br.com.project.portfolio.frontend.dao.DaoProjeto;
import main.java.br.com.project.portfolio.model.constants.Risco;
import main.java.br.com.project.portfolio.model.constants.Status;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/salvarProjeto")
public class Projeto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProjeto daoProjeto = new DaoProjeto();
	
	public Projeto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String acao = request.getParameter("acao");
			String projeto = request.getParameter("Projeto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProjeto.delete(projeto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProjeto.jsp");
				request.setAttribute("projetos", daoProjeto.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {

				BeansProjeto beansProjeto = daoProjeto.consultar(projeto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProjeto.jsp");
				request.setAttribute("projeto", beansProjeto);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProjeto.jsp");
				request.setAttribute("projetos", daoProjeto.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProjeto.jsp");
				request.setAttribute("projetos", daoProjeto.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String datainicio = request.getParameter("datainicio");
			String dataprevisaofim = request.getParameter("dataprevisaofim");
			String datafim = request.getParameter("datafim");
			String descricao = request.getParameter("descricao");
			String status = request.getParameter("status");
			String orcamento = request.getParameter("orcamento");
			String risco = request.getParameter("risco");

			BeansProjeto beansProjeto = new BeansProjeto();
			beansProjeto.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			beansProjeto.setNome(nome);
			beansProjeto.setDatainicio(Date.valueOf(datainicio));
			beansProjeto.setDataprevisaofim(Date.valueOf(dataprevisaofim));
			beansProjeto.setDatafim(Date.valueOf(datafim));
			beansProjeto.setDescricao(descricao);
			beansProjeto.setStatus(Status.ANALISE_APROVADA);
			beansProjeto.setOrcamento(Double.valueOf(orcamento));
			beansProjeto.setRisco(Risco.MEDIO);
			
			if(id == null || id.isEmpty() && !daoProjeto.validarProjeto(nome)) {
				request.setAttribute("msg","Projeto já existe com o mesmo nome!");
			}

			if (id == null || id.isEmpty() && daoProjeto.validarProjeto(nome)) {
				daoProjeto.salvar(beansProjeto);
			} else if(id != null && !id.isEmpty()) {
				daoProjeto.atualizar(beansProjeto);
			}

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProjeto.jsp");
				request.setAttribute("projetos", daoProjeto.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
