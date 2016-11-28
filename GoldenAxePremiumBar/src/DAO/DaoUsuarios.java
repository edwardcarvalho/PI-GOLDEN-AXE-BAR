package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Funcionario;
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
		String sql = "UPDATE USUARIO SET ATIVO = 0 WHERE ID_FUNCIONARIO = ?";
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

	public Usuario buscarUsuario(int id_funcionario) {
		String sql = "SELECT * FROM USUARIO WHERE ID_FUNCIONARIO = ? AND ATIVO = 1";
		Usuario usuario = null;
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_funcionario);
			rs = pst.executeQuery();

			while (rs.next()) {
				usuario = new Usuario(rs.getString("USUARIO"), rs.getString("SENHA"), rs.getInt("ID_FUNCIONARIO"));
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {

		}

		return usuario;
	}
	
	public Usuario checarUsuario(String user, String psw) {
		String sql = "SELECT U.USUARIO, U.SENHA, U.ID_FUNCIONARIO, F.GRUPO FROM USUARIOS AS U INNER JOIN FUNCIONARIO F ON U.ID_FUNCIONARIO = F.ID_FUNCIONARIO WHERE U.USUARIO = ? AND U.SENHA = ? AND U.ATIVO = 1";
		Usuario usuario = null;
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, psw);
			rs = pst.executeQuery();

			while (rs.next()) {
				usuario = new Usuario(rs.getString("USUARIO"), rs.getString("SENHA"), rs.getInt("ID_FUNCIONARIO"), rs.getInt("GRUPO"));
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}

		return usuario;
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
