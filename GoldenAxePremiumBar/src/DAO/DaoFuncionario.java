package DAO;

import Entity.Cliente;
import Entity.Funcionario;

public class DaoFuncionario extends ConnectionDAO {

	public int salvar(Funcionario funcionario) {

		String sql = "INSERT INTO FUNCIONARIO(NOME, CPF, SEXO, ID_UNIDADE, GRUPO, ATIVO) VALUES(?,?,?,?,?,1)";
		int id = 0;

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getCpf());
			pst.setBoolean(3, funcionario.getSexo());
			pst.setInt(4, funcionario.getUnidade());
			pst.setInt(5, funcionario.getGrupo());
			pst.execute();

			pst.close();
			rs = pst.getGeneratedKeys();
			id = rs.getInt(1);

			desconectaBanco();

			return id;

		} catch (Exception e) {

			return id;
		}

	}

	public boolean alterar(Funcionario funcionario) {

		String sql = "UPDATE FUNCIONARIO SET NOME=?,SEXO=?, ID_UNIDADE=?, GRUPO=? WHERE CPF=?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, funcionario.getNome());
			pst.setBoolean(2, funcionario.getSexo());
			pst.setInt(3, funcionario.getUnidade());
			pst.setInt(4, funcionario.getGrupo());
			pst.setString(5, funcionario.getCpf());
			pst.execute();

			pst.close();

			desconectaBanco();

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean deletar(int id_funcionario) {
		String sql = "DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_funcionario);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	public Funcionario buscarFuncionarioCPF(String cpf_funcionario) throws Exception {
		String sql = "SELECT * FROM FUNCIONARIO WHERE CPF = ? AND ATIVO = 1";
		Funcionario funcionario = null;
		
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cpf_funcionario);
			rs = pst.executeQuery();

			while (rs.next()) {
				funcionario = new Funcionario(rs.getInt("ID_FUNCIONARIO"),rs.getString("NOME"), rs.getString("CPF"), rs.getBoolean("SEXO"), rs.getInt("GRUPO"), rs.getInt("ID_UNIDADE"));
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			
		}
		
		return funcionario;
	}

	public int buscarIdFuncionarioPorCPF(String cpf_funcionario) throws Exception {
		String sql = "SELECT ID_FUNCIONARIO FROM FUNCIONARIO WHERE CPF = ? AND ATIVO = 1 LIMIT 1";
		int id = 0;
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cpf_funcionario);
			rs = pst.executeQuery();

			while (rs.next()) {
				id = rs.getInt("ID_FUNCIONARIO");
			}

			pst.close();
			desconectaBanco();

		} catch (Exception e) {

		}
		return id;
	}
}
