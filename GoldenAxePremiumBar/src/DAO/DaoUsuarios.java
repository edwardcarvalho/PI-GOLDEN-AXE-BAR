package DAO;

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
			// TODO: handle exception
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
			// TODO: handle exception
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
			// TODO: handle exception
		}
	}
	
}
