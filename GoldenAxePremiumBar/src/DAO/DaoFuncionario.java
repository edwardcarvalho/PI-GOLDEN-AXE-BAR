package DAO;

import Entity.Funcionario;

public class DaoFuncionario extends ConnectionDAO {

	public void salvar(Funcionario funcionario) {

		String sql = "INSERT INTO FUNCIONARIO(NOME, CPF, SEXO, ID_UNIDADE, TIPO) VALUES(?,?,?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getCpf());
			pst.setString(3, funcionario.getSexo().toString());
			pst.setString(4, funcionario.getUnidade());
			pst.setInt(5, funcionario.getTipo());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}

	}

	public void alterar(Funcionario funcionario) {

		String sql = "UPDATE FUNCIONARIO SET NOME=?, CPF=?,SEXO=?, ID_UNIDADE=?, TIPO=? WHERE ID_FUNCIONARIO=?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getCpf());
			pst.setString(3, funcionario.getSexo().toString());
			pst.setString(4, funcionario.getUnidade());
			pst.setInt(5, funcionario.getTipo());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void deletar(int id_funcionario) {
		String sql = "DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_funcionario);
			pst.execute();
			pst.close();

			desconectaBanco();
		} catch (Exception e) {
		}
	}
}
