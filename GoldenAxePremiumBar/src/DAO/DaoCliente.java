package DAO;

import java.sql.Date;

import Entity.Cliente;

public class DaoCliente extends ConnectionDAO {

	public boolean salvar(Cliente cliente) {

		String sql = "INSERT INTO CLIENTE (NOME, TELEFONE, CPF, SEXO, EMAIL, DATA_NASC) VALUES (?,?,?,?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cliente.getNome());
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

	public void alterar(Cliente cliente) {

		String sql = "UPDATE CLIENTE SET NOME = ?, TELEFONE = ?, CPF = ?, SEXO = ?, EMAIL = ?, DATA_NASC = ? WHERE ID_CLIENTE = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getTelefone());
			pst.setString(3, cliente.getCpf());
			pst.setBoolean(4, cliente.getSexo());
			pst.setString(5, cliente.getEmail());
			pst.setString(6, cliente.getDataNascimento());

			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void deletar(int id_Cliente) {
		String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Cliente);
			pst.execute();
			pst.close();

			desconectaBanco();
		} catch (Exception e) {
		}
	}

	public Cliente buscarClienteCPF(String cpf_cliente) throws Exception {
		String sql = "SELECT * FROM CLIENTE WHERE CPF = ?";
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
}
