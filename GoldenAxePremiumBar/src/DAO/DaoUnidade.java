package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Unidade;

public class DaoUnidade extends ConnectionDAO {

	public void salvar(Unidade unidade) {

		String sql = "INSERT INTO UNIDADE (NOME) VALUES (?)";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, unidade.getNome());
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void alterar(Unidade unidade) {
		String sql = "UPDATE UNIDADE SET NOME=? WHERE ID_UNIDADE = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, unidade.getNome());
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void deletar(int id_Unidade) {
		String sql = "DELETE FROM UNIDADE WHERE ID_UNIDADE = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Unidade);
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}

	}

	public Unidade procurarId(int id_unidade) throws Exception {
		String sql = "SELECT * FROM UNIDADE WHERE ID_UNIDADE = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_unidade);
			rs = pst.executeQuery();
		} catch (Exception e) {
		}
		Unidade unidade = new Unidade();
		while (rs.next()) {

			unidade.setNome(rs.getString("nome"));
		}
		pst.close();
		desconectaBanco();

		return unidade;
	}

	public List<Unidade> procurarNome(String nome) throws Exception {
		List<Unidade> lista = new ArrayList<Unidade>();
		String sql = "SELECT * FROM UNIDADE WHERE NOME LIKE '%" + nome + "%'";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, nome);
			rs = pst.executeQuery();

			while (rs.next()) {
				Unidade unidade = new Unidade();

				unidade.setNome("nome");
				lista.add(unidade);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}

	public List<Unidade> mostrarTodos() throws Exception {
		List<Unidade> lista = new ArrayList<Unidade>();
		String sql = "SELECT * FROM UNIDADE";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Unidade unidade = new Unidade();
			
				unidade.setNome(rs.getString("nome"));
				
				lista.add(unidade);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}

}
