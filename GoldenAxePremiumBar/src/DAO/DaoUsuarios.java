package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Usuarios;

public class DaoUsuarios extends ConnectionDAO {

	public void salvar(Usuarios usuarios) {

		String sql = "INSERT INTO USUARIO(ID_FUNCIONARIO, NOME_USU, SENHA,) VALUES(?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, usuarios.getId_Funcionario());
			pst.setString(2, usuarios.getUsuario());
			pst.setString(3, usuarios.getSenha());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}

	}

	public void alterar(Usuarios usuarios) {

		String sql = "UPDATE USUARIO SET ID_FUNCIONARIO = ?, NOME_USU = ?, SENHA = ? WHERE ID_USUARIO = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, usuarios.getId_Funcionario());
			pst.setString(2, usuarios.getUsuario());
			pst.setString(3, usuarios.getSenha());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void deletar(int id_Usuario) {
		String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Usuario);
			pst.execute();
			pst.close();

			desconectaBanco();
		} catch (Exception e) {
		}
	}
	public List<Usuarios> mostrarTodos() throws Exception {
		List<Usuarios> lista = new ArrayList<Usuarios>();
		String sql = "SELECT * FROM USUARIO";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Usuarios usuarios = new Usuarios();
			
				usuarios.setUsuario(rs.getString("USUARIO"));
				
				lista.add(usuarios);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}
	
}
