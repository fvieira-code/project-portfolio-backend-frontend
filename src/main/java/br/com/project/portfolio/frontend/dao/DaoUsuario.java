package main.java.br.com.project.portfolio.frontend.dao;

import main.java.br.com.project.portfolio.frontend.beans.BeansUsuario;
import main.java.br.com.project.portfolio.frontend.connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeansUsuario usuario) {

		String sql = "insert into usuarios (login,senha,nome,status) values (?,?,?,?)";

		try {
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
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

	public List<BeansUsuario> listar() throws SQLException {

		List<BeansUsuario> lista = new ArrayList<BeansUsuario>();

		String sql = "select * from usuarios";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeansUsuario beansPessoa = new BeansUsuario();
			
				beansPessoa.setId(resultSet.getLong("id"));
				beansPessoa.setLogin(resultSet.getString("login"));
				beansPessoa.setSenha(resultSet.getString("senha"));
				beansPessoa.setNome(resultSet.getString("nome"));
				beansPessoa.setStatus(resultSet.getBoolean("status"));
				
			lista.add(beansPessoa);
		}

		return lista;
	}

	public void delete(String id) {

		String sql = "delete from usuarios where id ='" + id + "'";

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

	public BeansUsuario consultar(String id) throws Exception {

		String sql = "select * from usuarios where id='" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeansUsuario beansPessoa = new BeansUsuario();
			
				beansPessoa.setId(resultSet.getLong("id"));
				beansPessoa.setLogin(resultSet.getString("login"));
				beansPessoa.setSenha(resultSet.getString("senha"));
				beansPessoa.setNome(resultSet.getString("nome"));
				beansPessoa.setStatus(resultSet.getBoolean("status"));

			return beansPessoa;
		}

		return null;
	}
	
	public boolean validarLogin(String login) {


		try {
			
			String sql = "select count(1) as qtd from usuarios where login='" + login + "'";

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

	public void atualizar(BeansUsuario usuario) {

		String sql = "update usuarios set login = ? , senha = ?, nome = ? , status = ? where id =" + usuario.getId();

		try {
			PreparedStatement update = connection.prepareStatement(sql);
			
				update.setString(1, usuario.getLogin());
				update.setString(2, usuario.getSenha());
				update.setString(3, usuario.getNome());
				update.setBoolean(4, usuario.getStatus());
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
