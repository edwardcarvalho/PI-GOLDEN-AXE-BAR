package DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.corba.se.pept.transport.Connection;

public class ConnectionDAO {

	public static Connection conn;
	public static PreparedStatement pst;
	public static ResultSet rs;

	public void conectaBanco() {

		// faz a abertura de uma conexão com o banco de dados

		String diretorio = System.getProperty("user.dir");

		try {
			Class.forName("org.sqlite.JDBC");
			conn = (Connection) DriverManager.getConnection("jdbc:sqlite:" + diretorio + "\\LuderiaBD.bd");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Banco Conectado!");

	}

	public void desconectaBanco() throws SQLException {

		// faz o fechamento da conexão aberta com o banco de dados.

		conn.close();
		System.out.println("Banco desconectado!");
	}

}
