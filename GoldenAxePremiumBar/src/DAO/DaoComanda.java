package DAO;

import Entity.Comanda;

public class DaoComanda extends ConnectionDAO {
	public void salvar(Comanda comanda) {

		String sql = "INSERT INTO COMANDA(ID_CLIENTE, ID_SERVICO, ID_PRODUTO, ID_JOGO, QUANTIDADE_HORAS, DATA_COMANDA, ID_FUNCIONARIO) VALUES(?,?,?,?,?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, comanda.getIdCliente());
			pst.setInt(2, comanda.getIdServico());
			pst.setInt(3, comanda.getIdProduto());
			pst.setInt(4, comanda.getIdJogo());
			pst.setInt(5, comanda.getQuantidade());
			pst.setDate(6, comanda.getDataComanda());
			pst.setInt(7, comanda.getIdFuncionario());

			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}

	}

	public void alterar(Comanda comanda) {

		String sql = "UPDATE COMANDA SET ID_CLIENTE=?, ID_SERVICO=?, ID_PRODUTO=?, ID_JOGO=?, QUANTIDADE_HORAS=?, DATA_COMANDA=?, ID_FUNCIONARIO=? WHERE ID_COMANDA = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, comanda.getIdCliente());
			pst.setInt(2, comanda.getIdServico());
			pst.setInt(3, comanda.getIdProduto());
			pst.setInt(4, comanda.getIdJogo());
			pst.setInt(5, comanda.getQuantidade());
			pst.setDate(6, comanda.getDataComanda());
			pst.setInt(7, comanda.getIdFuncionario());

			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}

	public void deletar(int id_comanda) {
		String sql = "DELETE FROM COMANDA WHERE ID_COMANDA = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_comanda);
			pst.execute();
			pst.close();

			desconectaBanco();
		} catch (Exception e) {
		}
	}

}
