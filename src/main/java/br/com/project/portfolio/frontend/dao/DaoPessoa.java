package main.java.br.com.project.portfolio.frontend.dao;

import main.java.br.com.project.portfolio.frontend.beans.BeansPessoa;
import main.java.br.com.project.portfolio.frontend.connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPessoa {

	private Connection connection;

	public DaoPessoa() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeansPessoa pessoa) {

		String sql = "insert into pessoas (nome, datanascimento, cpf, funcionario) values (?,?,?,?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, pessoa.getNome());
			insert.setDate(2,  (java.sql.Date) pessoa.getDatanascimento());
			insert.setString(3, pessoa.getCpf());
			insert.setBoolean(4, Boolean.TRUE);
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

	public List<BeansPessoa> listar() throws SQLException {

		List<BeansPessoa> lista = new ArrayList<BeansPessoa>();

		String sql = "select * from pessoas";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeansPessoa beansPessoa = new BeansPessoa();
				beansPessoa.setId(resultSet.getLong("id"));
				beansPessoa.setNome(resultSet.getString("nome"));
				beansPessoa.setDatanascimento(resultSet.getDate("dataNascimento"));
				beansPessoa.setCpf(resultSet.getString("cpf"));
				beansPessoa.setFuncionario(resultSet.getBoolean("funcionario"));
			lista.add(beansPessoa);
		}

		return lista;
	}

	public void delete(String id) {

		String sql = "delete from pessoas where id ='" + id + "'";

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

	public BeansPessoa consultar(String id) throws Exception {

		String sql = "select * from pessoas where id='" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			
			BeansPessoa beansPessoa = new BeansPessoa();
			
				beansPessoa.setId(resultSet.getLong("id"));
				beansPessoa.setNome(resultSet.getString("nome"));
				beansPessoa.setDatanascimento(resultSet.getDate("dataNascimento"));
				beansPessoa.setFuncionario(resultSet.getBoolean("funcionario"));
			
			return beansPessoa;
		}

		return null;
	}
	
	public boolean validarPessoa(String nome) {


		try {
			
			String sql = "select count(1) as qtd from pessoas where nome='" + nome + "'";

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

	public void atualizar(BeansPessoa pessoa) {

		String sql = "update pessoas set nome = ?, datanascimento = ?, cpf = ?, funcionario = ? where id =" + pessoa.getId();

		try {
			
			PreparedStatement update = connection.prepareStatement(sql);

				update.setString(1, pessoa.getNome());
				update.setDate(2,  (java.sql.Date) pessoa.getDatanascimento());
				update.setString(3, pessoa.getCpf());
				update.setBoolean(4, Boolean.TRUE);
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
