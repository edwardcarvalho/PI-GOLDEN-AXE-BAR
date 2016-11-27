package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Estoque;

public class DaoEstoque extends ConnectionDAO {

	public boolean salvar(Estoque estoque) {

		String sql = "INSERT INTO ESTOQUE (ID_FORNECEDOR,ID_PRODUTO,ID_JOGO,QUANTIDADE,VALOR,ID_UNIDADE,STATUS) VALUES (?,?,?,?,?,?,1)";
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
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateItemCadastrado(int idItem, int quantidade, float valor, int tipo) {
		String sql = "";
		
		if (tipo == 1)
			sql = "UPDATE ESTOQUE SET QUANTIDADE=QUANTIDADE+?, VALOR=? WHERE ID_PRODUTO = ?";
		else
			sql = "UPDATE ESTOQUE SET QUANTIDADE=QUANTIDADE+?, VALOR=? WHERE ID_JOGO = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, quantidade);
			pst.setFloat(2, valor);
			pst.setInt(3, idItem);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean alterar(Estoque estoque) {
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
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(int idItem, int tipo) {
		String sql = "";
		if(tipo == 1)
		sql = "UPDATE ESTOQUE SET STATUS = 0 WHERE ID_PRODUTO = ?";
		else
		sql = "UPDATE ESTOQUE SET STATUS = 0 WHERE ID_JOGO = ?";
		
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idItem);
			pst.execute();
			pst.close();
			
			pst = conn.prepareStatement("UPDATE PRODUTOS SET ATIVO = 0 WHERE ID_PRODUTO = ?");
			pst.setInt(1, idItem);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public Estoque procurarId(int id_estoque) throws Exception {
		String sql = "SELECT * FROM ESTOQUE WHERE ID_ESTOQUE = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_estoque);
			rs = pst.executeQuery();
		} catch (Exception e) {
		}
		Estoque estoque = new Estoque();
		while (rs.next()) {

			estoque.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
			estoque.setIdProduto(rs.getInt("ID_PRODUTO"));
			estoque.setIdJogo(rs.getInt("ID_JOGOS"));
			estoque.setQuantidade(rs.getInt("QUANTIDADE"));
			estoque.setValor(rs.getFloat("VALOR"));
			estoque.setIdUnidade(rs.getInt("ID_UNIDADE"));
		}
		pst.close();
		desconectaBanco();

		return estoque;
	}

	public List<Estoque> mostrarTodos() throws Exception {
		List<Estoque> lista = new ArrayList<Estoque>();
		String sql = "SELECT * FROM ESTOQUE";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Estoque estoque = new Estoque();

				estoque.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
				estoque.setIdProduto(rs.getInt("ID_PRODUTO"));
				estoque.setIdJogo(rs.getInt("ID_JOGOS"));
				estoque.setQuantidade(rs.getInt("QUANTIDADE"));
				estoque.setValor(rs.getFloat("VALOR"));
				estoque.setIdUnidade(rs.getInt("ID_UNIDADE"));

				lista.add(estoque);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}
	
	public int gerarNumeroEntradaProduto() throws Exception {
		String sql = "SELECT MAX(ID_ESTOQUE) AS ID_ESTOQUE FROM ESTOQUE";
		int id = 0;

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {

				id += rs.getInt("ID_ESTOQUE") == 0 ? 1 : rs.getInt("ID_ESTOQUE") + 1;

			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}

		return id;
	}

}
