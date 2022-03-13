package br.com.testeMaven.core.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.testeMaven.core.dao.IDAO;
import br.com.testeMaven.util.Conexao;

public abstract class AbstractJdbcDAO implements IDAO {
	
	protected Connection connection;
	
	protected void openConnection() {
        try {

            if (connection == null || connection.isClosed()) {
            	connection = Conexao.getConnection();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
