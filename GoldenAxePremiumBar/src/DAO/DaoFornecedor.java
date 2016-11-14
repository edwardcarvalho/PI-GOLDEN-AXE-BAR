package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Fornecedor;

public class DaoFornecedor extends ConnectionDAO {

	public boolean salvar(Fornecedor fornecedor) {

		String sql = "INSERT INTO FORNECEDOR (NOME) VALUES (?)";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fornecedor.getNome());

			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean alterar(Fornecedor fornecedor) {
		String sql = "UPDATE FORNECEDOR SET NOME=? WHERE ID_FORNECEDOR=?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fornecedor.getNome());
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(int id_fornecedor) {
		String sql = "DELETE FROM FORNECEDOR WHERE ID_FORNECEDOR = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_fornecedor);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
		public Fornecedor procurarId(int id_fornecedor) throws Exception {
			String sql = "SELECT * FROM FORNECEDOR WHERE ID_FORNECEDOR = ?";

			try {
				conectaBanco();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, id_fornecedor);
				rs = pst.executeQuery();
			} catch (Exception e) {
			}
			Fornecedor fornecedor = new Fornecedor();
			while (rs.next()) {

				fornecedor.setNome(rs.getString("NOME"));

			}
			pst.close();
			desconectaBanco();

			return fornecedor;
		}

		public List<Fornecedor> procurarNome(String nome) throws Exception {
			List<Fornecedor> lista = new ArrayList<Fornecedor>();
			String sql = "SELECT * FROM FORNECEDOR WHERE NOME LIKE '%" + nome + "%'";

			try {
				conectaBanco();
				pst = conn.prepareStatement(sql);
				pst.setString(1, nome);
				rs = pst.executeQuery();

				while (rs.next()) {
					Fornecedor fornecedor = new Fornecedor();

					fornecedor.setNome(rs.getString("NOME"));
				}
				pst.close();
				desconectaBanco();

			} catch (Exception e) {
			}
			return lista;
		}

		public List<Fornecedor> mostrarTodos() throws Exception {
			List<Fornecedor> lista = new ArrayList<Fornecedor>();
			String sql = "SELECT * FROM FORNECEDOR";

			try {
				conectaBanco();
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();

				while (rs.next()) {
					Fornecedor fornecedor = new Fornecedor();

					fornecedor.setNome(rs.getString("NOME"));

					lista.add(fornecedor);
				}
				pst.close();
				desconectaBanco();

			} catch (Exception e) {
			}
			return lista;
		}

	}

