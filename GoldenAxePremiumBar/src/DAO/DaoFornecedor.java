package DAO;

import Entity.Fornecedor;

public class DaoFornecedor extends ConnectionDAO {

	public void salvar(Fornecedor fornecedor) {

		String sql = "INSERT INTO FORNECEDOR (NOME) VALUES (?)";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fornecedor.getNome());

			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void alterar(Fornecedor fornecedor) {
		String sql = "UPDATE FORNECEDOR SET NOME=? WHERE ID_FORNECEDOR=?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fornecedor.getNome());
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void deletar(int id_fornecedor) {
		String sql = "DELETE FROM FORNECEDOR WHERE ID_FORNECEDOR = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_fornecedor);
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

}
