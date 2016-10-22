package DAO;

import Entity.Produto;

public class DaoProduto extends ConnectionDAO{
	public void salvar(Produto produto) {

		String sql = "INSERT INTO PRODUTOS(NOME, QUANTIDADE,VALOR, ID_FORNECEDOR) VALUES(?,?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setInt(2, produto.getQuantidade());
			pst.setFloat(3, produto.getValor());
			pst.setInt(4, produto.getIdFornecedor());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void alterar(Produto produto) {

		String sql = "UPDATE PRODUTOS SET NOME=?, QUANTIDADE=?,VALOR=?, ID_FORNECEDOR=? WHERE ID_PRODUTO=?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setInt(2, produto.getQuantidade());
			pst.setFloat(3, produto.getValor());
			pst.setInt(4, produto.getIdFornecedor());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(Integer id_Produto) {
		String sql = "DELETE FROM PRODUTOS WHERE ID_PRODUTO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Produto);
			pst.execute();
			pst.close();

			desconectaBanco();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
