package DAO;

import java.util.ArrayList;
import java.util.List;

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
	public Comanda procurarId(int id_comanda) throws Exception {
		String sql = "SELECT * FROM COMANDA WHERE ID_COMANDA = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_comanda);
			rs = pst.executeQuery();
		} catch (Exception e) {
		}
		Comanda comanda = new Comanda();
		while (rs.next()) {
			
			comanda.setIdCliente(rs.getInt("ID_CLIENTE"));
			comanda.setIdServico(rs.getInt("ID_SERVICO"));	
			comanda.setIdProduto(rs.getInt("ID_PRODUTO"));
			comanda.setIdJogo(rs.getInt("ID_JOGO"));
			comanda.setQuantidade(rs.getInt("QUANTIDADE_HORAS"));
			comanda.setDataComanda(rs.getDate("DATA_COMANDA"));
			comanda.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			
		}
		pst.close();
		desconectaBanco();

		return comanda;
	}

	public List<Comanda> mostrarTodos() throws Exception {
		List<Comanda> lista = new ArrayList<Comanda>();
		String sql = "SELECT * FROM COMANDA";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
			Comanda comanda = new Comanda();

			comanda.setIdCliente(rs.getInt("ID_CLIENTE"));
			comanda.setIdServico(rs.getInt("ID_SERVICO"));	
			comanda.setIdProduto(rs.getInt("ID_PRODUTO"));
			comanda.setIdJogo(rs.getInt("ID_JOGO"));
			comanda.setQuantidade(rs.getInt("QUANTIDADE_HORAS"));
			comanda.setDataComanda(rs.getDate("DATA_COMANDA"));
			comanda.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));

				lista.add(comanda);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}
	
}
