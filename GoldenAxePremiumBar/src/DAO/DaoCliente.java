package DAO;

import Entity.Cliente;

public class DaoCliente extends ConnectionDAO {

	public void salvar(Cliente cliente) {

		String sql = "INSERT INTO CLIENTE(NOME, TELEFONE, CPF, SEXO, EMAIL, DATA_NASC) VALUES(?,?,?,?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getTelefone());
			pst.setString(3, cliente.getCpf());
			pst.setString(4, cliente.getSexo().toString());
			pst.setString(5, cliente.getEmail());
			pst.setDate(6, cliente.getDataNascimento());

			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
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
			pst.setString(4, cliente.getSexo().toString());
			pst.setString(5, cliente.getEmail());
			pst.setDate(6, cliente.getDataNascimento());

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
}
