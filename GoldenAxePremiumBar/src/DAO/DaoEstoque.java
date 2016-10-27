package DAO;

import Entity.Estoque;

public class DaoEstoque extends ConnectionDAO {

	public void salvar(Estoque estoque) {

		String sql = "INSERT INTO ESTOQUE (ID_FORNECEDOR,ID_PRODUTO,ID_JOGO,QUANTIDADE,VALOR,ID_UNIDADE) VALUES (?,?,?,?,?,?)";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, estoque.getIdFornecedor());
			pst.setInt(2, estoque.getIdProduto());
			pst.setInt(3, estoque.getIdJogo());
			pst.setInt(4, estoque.getQuantidade());
			pst.setFloat(5, estoque.getValor());
			pst.setInt(6, estoque.getIdUnidade());

			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void alterar(Estoque estoque) {
		String sql = "UPDATE ESTOQUE SET ID_FORNECEDOR=?,ID_PRODUTO=?,ID_JOGO=?,QUANTIDADE=?,VALOR=?,ID_UNIDADE=? WHERE ID_ESTOQUE=?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, estoque.getIdFornecedor());
			pst.setInt(2, estoque.getIdProduto());
			pst.setInt(3, estoque.getIdJogo());
			pst.setInt(4, estoque.getQuantidade());
			pst.setFloat(5, estoque.getValor());
			pst.setInt(6, estoque.getIdUnidade());
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void deletar(int id_estoque) {
		String sql = "DELETE FROM ESTOQUE WHERE ID_ESTOQUE = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_estoque);
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}
}
