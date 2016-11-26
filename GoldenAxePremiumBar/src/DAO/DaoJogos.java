package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Jogos;

public class DaoJogos extends ConnectionDAO {
	public boolean salvar(Jogos jogos) {

		String sql = "INSERT INTO JOGOS(NOME, QUANTIDADE,VALOR,ID_FORNECEDOR, ATIVO) VALUES(?,?,?,?,1)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, jogos.getNome());
			pst.setInt(2, jogos.getQuantidade());
			pst.setFloat(3, jogos.getValor());
			pst.setInt(3, jogos.getIdFornecedor());
			pst.execute();

			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean alterar(Jogos jogos) {

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
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(int id_jogos) {
		String sql = "DELETE FROM JOGOS WHERE ID_JOGOS = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_jogos);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	public Jogos procurarId(int id_jogos) throws Exception {
		String sql = "SELECT * FROM JOGOS WHERE ID_JOGOS = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_jogos);
			rs = pst.executeQuery();
		} catch (Exception e) {
		}
		Jogos jogos = new Jogos();
		while (rs.next()) {

			jogos.setNome(rs.getString("NOME"));
			jogos.setQuantidade(rs.getInt("QUANTIDADE"));
			jogos.setValor(rs.getFloat("VALOR"));
			jogos.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
		}
		pst.close();
		desconectaBanco();

		return jogos;
	}

	public List<Jogos> procurarNome(String nome) throws Exception {
		List<Jogos> lista = new ArrayList<Jogos>();
		String sql = "SELECT * FROM JOGOS WHERE NOME LIKE '%" + nome + "%'";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, nome);
			rs = pst.executeQuery();

			while (rs.next()) {
				Jogos jogos = new Jogos();

				jogos.setNome(rs.getString("NOME"));
				jogos.setQuantidade(rs.getInt("QUANTIDADE"));
				jogos.setValor(rs.getFloat("VALOR"));
				jogos.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}

	public List<Jogos> mostrarTodos() throws Exception {
		List<Jogos> lista = new ArrayList<Jogos>();
		String sql = "SELECT * FROM JOGOS";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Jogos jogos = new Jogos();

				jogos.setIdJogo(rs.getInt("ID_JOGOS"));
				jogos.setNome(rs.getString("NOME"));
				jogos.setQuantidade(rs.getInt("QUANTIDADE"));
				jogos.setValor(rs.getFloat("VALOR"));
				jogos.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));

				lista.add(jogos);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}

}
