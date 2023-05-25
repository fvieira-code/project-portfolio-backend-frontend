package main.java.br.com.project.portfolio.frontend.dao;

import main.java.br.com.project.portfolio.frontend.beans.BeansProjeto;
import main.java.br.com.project.portfolio.frontend.connection.SingleConnection;
import main.java.br.com.project.portfolio.model.constants.Risco;
import main.java.br.com.project.portfolio.model.constants.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProjeto {

	private Connection connection;

	public DaoProjeto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeansProjeto projeto) {

		String sql = "insert into projetos (nome, dataa_inicio, data_previsao_fim, data_fim, descricao, status, orcamento, risco) values (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, projeto.getNome());
			insert.setDate(2, (java.sql.Date) projeto.getDatainicio());
			insert.setDate(3, (java.sql.Date) projeto.getDataprevisaofim());
			insert.setDate(4, (java.sql.Date) projeto.getDatafim());
			insert.setString(5, projeto.getDescricao());
			insert.setString(6, projeto.getStatus().toString());
			insert.setDouble(7, projeto.getOrcamento());
			insert.setBoolean(8, Boolean.TRUE);
			insert.execute();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<BeansProjeto> listar() throws SQLException {

		List<BeansProjeto> lista = new ArrayList<BeansProjeto>();

		String sql = "select * from projetos";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeansProjeto beansProjeto = new BeansProjeto();
				beansProjeto.setId(resultSet.getLong("id"));
				beansProjeto.setNome(resultSet.getString("nome"));
				beansProjeto.setDatainicio(resultSet.getDate("data_inicio"));
				beansProjeto.setDataprevisaofim(resultSet.getDate("data_previsao_fim"));
				beansProjeto.setDatafim(resultSet.getDate("data_fim"));
				beansProjeto.setDescricao(resultSet.getString("descricao"));
				beansProjeto.setStatus(Status.valueOf(resultSet.getString("status")));
				beansProjeto.setOrcamento(resultSet.getDouble("orcamento"));
				beansProjeto.setRisco(Risco.valueOf(resultSet.getString("risco")));
			lista.add(beansProjeto);
		}

		return lista;
	}

	public void delete(String id) {

		String sql = "delete from projetos where id ='" + id + "'";

		try {

			PreparedStatement delete = connection.prepareStatement(sql);
				delete.execute();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public BeansProjeto consultar(String id) throws Exception {

		String sql = "select * from projetos where id='" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			
			BeansProjeto beansProjeto = new BeansProjeto();
			
				beansProjeto.setId(resultSet.getLong("id"));
				beansProjeto.setNome(resultSet.getString("nome"));
				beansProjeto.setDatainicio(resultSet.getDate("data_inicio"));
				beansProjeto.setDataprevisaofim(resultSet.getDate("data_previsao_fim"));
				beansProjeto.setDatafim(resultSet.getDate("data_fim"));
				beansProjeto.setDescricao(resultSet.getString("descricao"));
				beansProjeto.setStatus(Status.ANALISE_APROVADA);
				beansProjeto.setOrcamento(resultSet.getDouble("orcamento"));
				beansProjeto.setRisco(Risco.MEDIO);
			
			return beansProjeto;
		}

		return null;
	}
	
	public boolean validarProjeto(String nome) {


		try {
			
			String sql = "select count(1) as qtd from projetos where nome='" + nome + "'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet;
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {

				return resultSet.getInt("qtd") <= 0 ;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	
	}

	public void atualizar(BeansProjeto projeto) {

		String sql = "update projetos set nome = ?, data_inicio = ?, data_previsao_fim = ?, data_fim = ?, descricao = ?, status = ?, orcamento = ?, risco = ? where id =" + projeto.getId();

		try {
			
			PreparedStatement update = connection.prepareStatement(sql);

				update.setString(1, projeto.getNome());
				update.setDate(2, (java.sql.Date) projeto.getDatainicio());
				update.setDate(3, (java.sql.Date) projeto.getDataprevisaofim());
				update.setDate(4, (java.sql.Date) projeto.getDatafim());
				update.setString(5, projeto.getDescricao());
				update.setString(6, projeto.getStatus().name());
				update.setDouble(7, projeto.getOrcamento());
				update.setString(8, projeto.getRisco().name());
				
				update.executeUpdate();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

}
