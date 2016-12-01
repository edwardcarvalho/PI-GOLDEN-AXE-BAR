package DAO;

import java.sql.Date;

import Entity.Cliente;

public class DaoCliente extends ConnectionDAO {

	public boolean salvar(Cliente cliente) {

		String sql = "INSERT INTO CLIENTE (NOME, TELEFONE, CPF, SEXO, EMAIL, DATA_NASC, ATIVO) VALUES (?,?,?,?,?,?,1)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cliente.getNome().toUpperCase());
			pst.setString(2, cliente.getTelefone());
			pst.setString(3, cliente.getCpf());
			pst.setBoolean(4, cliente.getSexo());
			pst.setString(5, cliente.getEmail());
			pst.setString(6, cliente.getDataNascimento());

			pst.executeUpdate();

			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			System.out.println("ERRO " + e);
			return false;
		}

	}

	public boolean alterar(Cliente cliente) {

		String sql = "UPDATE CLIENTE SET NOME = ?, TELEFONE = ?, SEXO = ?, EMAIL = ?, DATA_NASC = ? WHERE CPF = ? AND ATIVO = 1";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cliente.getNome().toUpperCase());
			pst.setString(2, cliente.getTelefone());
			pst.setBoolean(3, cliente.getSexo());
			pst.setString(4, cliente.getEmail());
			pst.setString(5, cliente.getDataNascimento());
			pst.setString(6, cliente.getCpf());

			pst.executeUpdate();

			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(int id_Cliente) {
		String sql = "UPDATE CLIENTE SET ATIVO = 0 WHERE ID_CLIENTE = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Cliente);
			pst.execute();
			pst.close();
			desconectaBanco();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Cliente buscarClienteCPF(String cpf_cliente) throws Exception {
		String sql = "SELECT * FROM CLIENTE WHERE CPF = ? AND ATIVO = 1";
		Cliente cliente = null;
		
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cpf_cliente);
			rs = pst.executeQuery();

			while (rs.next()) {
				cliente = new Cliente(rs.getString("NOME"), rs.getString("CPF"), rs.getString("EMAIL"),
						rs.getBoolean("SEXO"), rs.getString("DATA_NASC"), rs.getString("TELEFONE"));
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			
		}
		
		return cliente;
	}

	public int buscarIdClientePorCPF(String cpf_cliente) throws Exception {
		String sql = "SELECT ID_CLIENTE FROM CLIENTE WHERE CPF = ? AND ATIVO = 1 LIMIT 1";
		int id = 0;
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cpf_cliente);
			rs = pst.executeQuery();

			while (rs.next()) {
				id = rs.getInt("ID_CLIENTE");
			}

			pst.close();
			desconectaBanco();

		} catch (Exception e) {

		}
		return id;
	}
}
