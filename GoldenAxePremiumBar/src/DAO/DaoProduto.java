package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Produto;

public class DaoProduto extends ConnectionDAO {
	public int salvar(Produto produto) {

		String sql = "INSERT INTO PRODUTOS(NOME,VALOR,ID_FORNECEDOR,ATIVO) VALUES(?,?,?,1)";
		String sql1 = "SELECT MAX(ID_PRODUTO) AS ID_PRODUTO FROM PRODUTOS";
		int id = 0;

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, produto.getNome().toUpperCase());
			pst.setFloat(2, produto.getValor());
			pst.setInt(3, produto.getIdFornecedor());
			pst.execute();
			pst.close();
			
			pst = conn.prepareStatement(sql1);
			rs = pst.executeQuery();
			
			while(rs.next()){
				id = rs.getInt("ID_PRODUTO");
			}

			desconectaBanco();
			return id;

		} catch (Exception e) {
			return id;
		}

	}

	public boolean alterar(Produto produto) {

		String sql = "UPDATE PRODUTOS SET NOME=?, QUANTIDADE=?,VALOR=?, ID_FORNECEDOR=? WHERE ID_PRODUTO=?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, produto.getNome().toUpperCase());
			pst.setInt(2, produto.getQuantidade());
			pst.setFloat(3, produto.getValor());
			pst.setInt(4, produto.getIdFornecedor());
			pst.execute();

			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(int id_Produto) {
		String sql = "DELETE FROM PRODUTOS WHERE ID_PRODUTO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Produto);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Produto procurarId(int id_produto) throws Exception {
		String sql = "SELECT * FROM PRODUTO WHERE ID_PRODUTO = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_produto);
			rs = pst.executeQuery();
		} catch (Exception e) {
		}
		Produto produto = new Produto();
		while (rs.next()) {

			produto.setNome(rs.getString("NOME"));
			produto.setQuantidade(rs.getInt("QUANTIDADE"));
			produto.setValor(rs.getFloat("VALOR"));
			produto.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
		}
		pst.close();
		desconectaBanco();

		return produto;
	}

	public List<Produto> procurarNome(String nome) throws Exception {
		List<Produto> lista = new ArrayList<Produto>();
		String sql = "SELECT * FROM PRODUTO WHERE NOME LIKE '%" + nome + "%'";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, nome);
			rs = pst.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();

				produto.setNome(rs.getString("NOME"));
				produto.setQuantidade(rs.getInt("QUANTIDADE"));
				produto.setValor(rs.getFloat("VALOR"));
				produto.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
				lista.add(produto);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}

	public List<Produto> mostrarTodos() throws Exception {
		List<Produto> lista = new ArrayList<Produto>();
		String sql = "SELECT P.ID_PRODUTO, P.NOME, E.QUANTIDADE , P.VALOR, P.ID_FORNECEDOR FROM PRODUTOS AS P INNER JOIN ESTOQUE AS E ON E.ID_PRODUTO = P.ID_PRODUTO WHERE P.ATIVO = 1";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				
				produto.setId(rs.getInt("ID_PRODUTO"));
				produto.setNome(rs.getString("NOME"));
				produto.setQuantidade(rs.getInt("QUANTIDADE"));
				produto.setValor(rs.getFloat("VALOR"));
				produto.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));

				lista.add(produto);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}
	
	public Produto buscarProduto(String product) throws Exception {
		
		String sql = "SELECT P.ID_PRODUTO, P.NOME, E.QUANTIDADE , P.VALOR, P.ATIVO FROM PRODUTOS AS P INNER JOIN ESTOQUE AS E ON E.ID_PRODUTO = P.ID_PRODUTO WHERE P.NOME = '"+product+"' COLLATE NOCASE LIMIT 1";
		Produto produto = new Produto();
		
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				produto.setId(rs.getInt("ID_PRODUTO"));
				produto.setNome(rs.getString("NOME"));
				produto.setValor(rs.getFloat("VALOR"));
				produto.setQuantidade(rs.getInt("QUANTIDADE"));
				
			}
			
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			
			System.out.println(e);
		}
		return produto;
	}

}
