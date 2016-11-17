package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDAO {

	public static Connection conn;
	public static PreparedStatement pst;
	public static ResultSet rs;

	public void conectaBanco() {

		// faz a abertura de uma conexão com o banco de dados

//		String diretorio = System.getProperty("user.dir");
		String diretorio = "C:\\Users\\Jeff.Soul\\Desktop\\LuderiaBD.db";
//		String diretorio = "C:\\Users\\Jeff.Soul\\Desktop\\LuderiaBD.db";

		try {
			Class.forName("org.sqlite.JDBC");
//			conn = (Connection) DriverManager.getConnection("jdbc:sqlite:" + diretorio + "\\LuderiaBD");
			conn = (Connection) DriverManager.getConnection("jdbc:sqlite:" + diretorio);
			System.out.println("Banco Conectado!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha na conexão!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Falha na conexão!");
		}

	}

	public void desconectaBanco() throws SQLException {

		// faz o fechamento da conexão aberta com o banco de dados.

		conn.close();
		System.out.println("Banco desconectado!");
	}

}