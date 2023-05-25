package main.java.br.com.project.portfolio.frontend.servlet;

import main.java.br.com.project.portfolio.frontend.beans.BeansPessoa;
import main.java.br.com.project.portfolio.frontend.dao.DaoPessoa;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/salvarPessoa")
public class Pessoa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoPessoa daoPessoa = new DaoPessoa();
	
	public Pessoa() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String acao = request.getParameter("acao");
			String pessoa = request.getParameter("pessoa");

			if (acao.equalsIgnoreCase("delete")) {
				daoPessoa.delete(pessoa);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroPessoa.jsp");
				request.setAttribute("pessoas", daoPessoa.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {

				BeansPessoa beansPessoa = daoPessoa.consultar(pessoa);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroPessoa.jsp");
				request.setAttribute("pessoa", beansPessoa);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroPessoa.jsp");
				request.setAttribute("pessoas", daoPessoa.listar());
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

				RequestDispatcher view = request.getRequestDispatcher("/cadastroPessoa.jsp");
				request.setAttribute("pessoas", daoPessoa.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String datanascimento = request.getParameter("datanascimento");
			String cpf = request.getParameter("cpf");
			String funcionario = request.getParameter("funcionario");

			BeansPessoa pessoa = new BeansPessoa();
			pessoa.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			pessoa.setNome(nome);
			pessoa.setDatanascimento(Date.valueOf(datanascimento));
			pessoa.setCpf(cpf);
			pessoa.setFuncionario(Boolean.valueOf(funcionario));
			
			if(id == null || id.isEmpty() && !daoPessoa.validarPessoa(nome)) {
				request.setAttribute("msg","Pessoa já existe com o mesmo nome!");
			}

			if (id == null || id.isEmpty() && daoPessoa.validarPessoa(nome)) {
				daoPessoa.salvar(pessoa);
			} else if(id != null && !id.isEmpty()) {
				daoPessoa.atualizar(pessoa);
			}

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroPessoa.jsp");
				request.setAttribute("pessoas", daoPessoa.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
