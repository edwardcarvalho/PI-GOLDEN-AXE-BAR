package DAO;

import Entity.Jogos;

public class DaoJogos extends ConnectionDAO {
	public void salvar(Jogos jogos) {

		String sql = "INSERT INTO JOGOS(NOME, QUANTIDADE,VALOR, ID_FORNECEDOR) VALUES(?,?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, jogos.getNome());
			pst.setInt(2, jogos.getQuantidade());
			pst.setFloat(3, jogos.getValor());
			pst.setInt(4, jogos.getIdFornecedor());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void alterar(Jogos jogos) {

		String sql = "UPDATE JOGOS SET NOME=?, QUANTIDADE=?,VALOR=?, ID_FORNECEDOR=? WHERE ID_JOGOS=?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, jogos.getNome());
			pst.setInt(2, jogos.getQuantidade());
			pst.setFloat(3, jogos.getValor());
			pst.setInt(4, jogos.getIdFornecedor());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(int id_Jogos) {
		String sql = "DELETE FROM JOGOS WHERE ID_JOGOS = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Jogos);
			pst.execute();
			pst.close();

			desconectaBanco();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
