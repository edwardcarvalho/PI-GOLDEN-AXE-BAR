package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Produto;

public class DaoProduto extends ConnectionDAO {
	public boolean salvar(Produto produto) {

		String sql = "INSERT INTO PRODUTOS(NOME, QUANTIDADE,VALOR) VALUES(?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setInt(2, produto.getQuantidade());
			pst.setFloat(3, produto.getValor());
			pst.execute();

			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean alterar(Produto produto) {

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
		String sql = "SELECT * FROM PRODUTO";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
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
	
	public Produto buscarProduto(String product) throws Exception {
		
		String sql = "SELECT * FROM PRODUTOS WHERE NOME = '"+product+"' COLLATE NOCASE LIMIT 1";
		Produto produto = new Produto();
		
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				produto.setId(rs.getInt("ID_PRODUTO"));
				produto.setNome(rs.getString("NOME"));
				produto.setValor(rs.getFloat("VALOR"));
				
			}
			
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			
			System.out.println(e);
		}
		return produto;
	}

}
