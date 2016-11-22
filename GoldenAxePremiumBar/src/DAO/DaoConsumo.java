package DAO;

import Entity.Comanda;
import Entity.Consumo;

public class DaoConsumo extends ConnectionDAO{
	
	public boolean salvar(Consumo consumo) {

		String sql = "INSERT INTO CONSUMO(ID_PRODUTO, ID_COMANDA, QUANTIDADE, STATUS) VALUES(?,?,?,1)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, consumo.getIdProduto());
			pst.setInt(2, consumo.getIdComanda());
			pst.setInt(3, consumo.getQuantidade());
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

}
