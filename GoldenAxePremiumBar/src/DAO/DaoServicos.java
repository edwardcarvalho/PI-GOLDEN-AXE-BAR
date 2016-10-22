package DAO;

import Entity.Servico;

public class DaoServicos extends ConnectionDAO {

	public void salvar(Servico servico) {

		String sql = "INSERT INTO SERVICO (DESCRICAO, VALOR_HORA) VALUES (?,?)";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, servico.getDescricao());
			pst.setFloat(2, servico.getValorHora());

			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void alterar(Servico servico) {
		String sql = "UPDATE SERVICO SET DESCRICAO=?, VALOR_HORA = ? WHERE ID_SERVICO=?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, servico.getDescricao());
			pst.setFloat(2, servico.getValorHora());
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(Integer id_Servico) {
		String sql = "DELETE FROM SERVICO WHERE ID_SERVICO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Servico);
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
