package main.java.br.com.project.portfolio.frontend.dao;

import main.java.br.com.project.portfolio.frontend.connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		
		connection = SingleConnection.getConnection();
		
	}
	
	public boolean validarLogin(String login, String senha) throws Exception{
		String sql = "select * from usuarios where login = '"
				+ login + "' and senha = '" + senha + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}else {
			return false;
		}
		
	}

}
