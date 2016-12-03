package DAO;

import java.util.ArrayList;
import Entity.Consumo;
import Entity.ConsumoComandaResponseEntity;

public class DaoConsumo extends ConnectionDAO {

//	public boolean salvar(Consumo consumo) {
//
//		String sql = "INSERT INTO CONSUMO(ID_PRODUTO, ID_COMANDA, QUANTIDADE, STATUS) VALUES(?,?,?,1)";
//
//		try {
//			conectaBanco();
//			pst = conn.prepareStatement(sql);
//			pst.setInt(1, consumo.getIdProduto());
//			pst.setInt(2, consumo.getIdComanda());
//			pst.setInt(3, consumo.getQuantidade());
//			pst.execute();
//			pst.close();
//
//			desconectaBanco();
//			return true;
//
//		} catch (Exception e) {
//			System.out.println(e);
//			return false;
//		}
//
//	}
	
	public boolean salvar(Consumo consumo) {
		 
	       String sql = "INSERT INTO CONSUMO(ID_PRODUTO, ID_COMANDA, QUANTIDADE, STATUS) VALUES(?,?,?,1)";
	       String sql1 = "UPDATE ESTOQUE SET QUANTIDADE = QUANTIDADE-? WHERE ID_PRODUTO = ?";
	 
	try {
	    conectaBanco();
	    pst = conn.prepareStatement(sql);
	    pst.setInt(1, consumo.getIdProduto());
	    pst.setInt(2, consumo.getIdComanda());
	    pst.setInt(3, consumo.getQuantidade());
	    pst.execute();
	    pst.close();
	 
	    pst = conn.prepareStatement(sql1);
	    pst.setInt(1, consumo.getQuantidade());
	    pst.setInt(2, consumo.getIdProduto());
	    pst.execute();
	    pst.close();
	 
	    desconectaBanco();
	    return true;
	 
	} catch (Exception e) {
	    System.out.println(e);
	    return false;
	}
	 
	}

	public boolean alterar(Consumo consumo) {

		String sql = "UPDATE CONSUMO SET QUANTIDADE = ?, ID_PRODUTO = ? WHERE ID = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, consumo.getQuantidade());
			pst.setInt(2, consumo.getIdProduto());
			pst.setInt(3, consumo.getId());
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean excluir(int idItem, int idComanda) {

		String sql = "UPDATE CONSUMO SET STATUS = 0 WHERE ID = ? AND ID_COMANDA = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idItem);
			pst.setInt(2, idComanda);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public ArrayList<ConsumoComandaResponseEntity> buscarConsumoIdComanda(int id) {

		String sql = "SELECT CLI.NOME, CLI.CPF, COM.ID_JOGO, COM.ID_SERVICO, COM.QUANTIDADE_HORAS FROM CLIENTE AS CLI "
				+ "INNER JOIN COMANDA AS COM ON CLI.ID_CLIENTE = COM.ID_CLIENTE WHERE COM.ID_COMANDA = ? AND COM.STATUS = 1";
		
		String sql1 = "SELECT CLI.NOME, CLI.CPF, COM.ID_JOGO, COM.ID_SERVICO, CONS.ID AS ID_CONSUMO, CONS.ID_PRODUTO, COM.QUANTIDADE_HORAS, CONS.QUANTIDADE, P.NOME AS PRODUTO, P.VALOR FROM CLIENTE AS CLI "
				+ "INNER JOIN COMANDA AS COM ON CLI.ID_CLIENTE = COM.ID_CLIENTE "
				+ "INNER JOIN CONSUMO AS CONS ON COM.ID_COMANDA = CONS.ID_COMANDA "
				+ "INNER JOIN PRODUTOS AS P ON CONS.ID_PRODUTO = P.ID_PRODUTO "
				+ "WHERE COM.ID_COMANDA = ? AND CONS.STATUS = 1 AND COM.STATUS = 1";

		ArrayList<ConsumoComandaResponseEntity> lista = new ArrayList<ConsumoComandaResponseEntity>();

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {

				ConsumoComandaResponseEntity consumo = new ConsumoComandaResponseEntity();
				consumo.setIdComanda(id);
				consumo.setNome(rs.getString("NOME"));
				consumo.setCpf(rs.getString("CPF"));
				consumo.setJogo(rs.getInt("ID_JOGO"));
				consumo.setTipoServico(rs.getInt("ID_SERVICO"));
				consumo.setHorasReservadas(rs.getString("QUANTIDADE_HORAS"));
				lista.add(consumo);
			}
			pst.close();
			
			pst = conn.prepareStatement(sql1);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {

				ConsumoComandaResponseEntity consumo = new ConsumoComandaResponseEntity();
				consumo.setIdProduto(rs.getInt("ID_PRODUTO"));
				consumo.setIdItemConsumo(rs.getInt("ID_CONSUMO"));
				consumo.setQuantidadeItemConsumo(rs.getInt("QUANTIDADE"));
				consumo.setNomeItemConsumo(rs.getString("PRODUTO"));
				consumo.setPrecoUnitario(rs.getFloat("VALOR"));
				lista.add(consumo);
			}
			pst.close();
			
			
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}

		return lista;
	}

}
