package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Usuario;

public class DaoUsuarios extends ConnectionDAO {

	public boolean salvar(Usuario usuarios) {

		String sql = "INSERT INTO USUARIO(ID_FUNCIONARIO, USUARIO, SENHA, ATIVO) VALUES(?,?,?,1)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, usuarios.getIdFuncionario());
			pst.setString(2, usuarios.getUsuario());
			pst.setString(3, usuarios.getSenha());
			pst.execute();

			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {

			return false;
		}

	}

	public boolean alterar(Usuario usuarios) {

		String sql = "UPDATE USUARIO SET USUARIO = ?, SENHA = ? WHERE ID_FUNCIONARIO = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, usuarios.getUsuario());
			pst.setString(2, usuarios.getSenha());
			pst.setInt(3, usuarios.getIdFuncionario());
			pst.execute();

			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(int id_Usuario) {
		String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Usuario);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	public List<Usuario> mostrarTodos() throws Exception {
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT * FROM USUARIO";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				// Usuario usuarios = new Usuario();
				//
				// usuarios.setUsuario(rs.getString("USUARIO"));
				//
				// lista.add(usuarios);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}

}
