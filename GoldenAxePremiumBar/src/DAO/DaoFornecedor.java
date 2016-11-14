package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Cliente;
import Entity.Fornecedor;

public class DaoFornecedor extends ConnectionDAO {

	public boolean salvar(Fornecedor fornecedor) {

		String sql = "INSERT INTO FORNECEDOR (NOME, CNPJ, EMAIL, TELEFONE, ATIVO) VALUES (?,?,?,?,1)";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fornecedor.getNome());
			pst.setString(2, fornecedor.getCnpj());
			pst.setString(3, fornecedor.getEmail());
			pst.setString(4, fornecedor.getTelefone());
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean alterar(Fornecedor fornecedor) {
		String sql = "UPDATE FORNECEDOR SET NOME = ?, TELEFONE = ?, EMAIL = ? WHERE CNPJ = ? AND ATIVO = 1";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fornecedor.getNome());
			pst.setString(2, fornecedor.getTelefone());
			pst.setString(3, fornecedor.getEmail());
			pst.setString(4, fornecedor.getCnpj());
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(String cnpj) {
		String sql = "UPDATE FORNECEDOR SET ATIVO = 0 WHERE CNPJ = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cnpj);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public Fornecedor buscarFornecedorPorCNPJ(String cnpj) throws Exception {
		String sql = "SELECT * FROM FORNECEDOR WHERE CNPJ = ? AND ATIVO = 1";
		Fornecedor fornecedor = null;
		
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cnpj);
			rs = pst.executeQuery();

			while (rs.next()) {
				fornecedor = new Fornecedor(rs.getString("CNPJ"), rs.getString("NOME"), rs.getString("TELEFONE"),
						rs.getString("EMAIL"));
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			
		}
		
		return fornecedor;
	}

	public int buscarIdFornecedorPorCnpj(String cnpj) throws Exception {
		String sql = "SELECT ID_FORNECEDOR FROM FORNECEDOR WHERE CNPJ = ? AND ATIVO = 1 LIMIT 1";
		int id = 0;
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cnpj);
			rs = pst.executeQuery();

			while (rs.next()) {
				id = rs.getInt("ID_FORNECEDOR");
			}

			pst.close();
			desconectaBanco();

		} catch (Exception e) {

		}
		return id;
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

