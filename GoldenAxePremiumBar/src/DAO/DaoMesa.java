package DAO;

import java.util.ArrayList;
import java.util.List;
import Servlet.Mesa;

public class DaoMesa extends ConnectionDAO{

	public List<Mesa> mostrarTodas() throws Exception {
		List<Mesa> lista = new ArrayList<Mesa>();
		String sql = "SELECT * FROM MESA";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Mesa mesa = new Mesa();

				mesa.setId(rs.getInt("ID"));
				mesa.setNumero(rs.getInt("NUMERO_MESA"));
				mesa.setTipo(rs.getInt("TIPO"));
				mesa.setDescricao(rs.getString("DESCRICAO"));
				mesa.setStatus(rs.getInt("STATUS"));
				mesa.setIdComanda(rs.getInt("ID_COMANDA"));

				lista.add(mesa);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;
	}
}
