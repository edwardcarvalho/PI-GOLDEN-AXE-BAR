package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Comanda;

public class DaoComanda extends ConnectionDAO {

	public boolean salvar(Comanda comanda) {

		String sql = "INSERT INTO COMANDA(ID_CLIENTE, ID_SERVICO, ID_JOGO, QUANTIDADE_HORAS, DATA_COMANDA, ID_FUNCIONARIO, STATUS) VALUES(?,?,?,?,?,?, 1)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, comanda.getIdCliente());
			pst.setInt(2, comanda.getIdServico());
			pst.setInt(3, comanda.getIdJogo());
			pst.setInt(4, comanda.getQuantidadeHoras());
			pst.setString(5, comanda.getDataComanda());
			pst.setInt(6, comanda.getIdFuncionario());
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean clientePossuiMesa(int idComanda) {

		String sql = "SELECT NUMERO_MESA FROM COMANDA WHERE ID_COMANDA = ? AND STATUS = 1 LIMIT 1";
		int idMesa = 0;

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idComanda);
			rs = pst.executeQuery();
			while (rs.next()) {

				idMesa += rs.getInt("NUMERO_MESA");

			}
			pst.close();
			desconectaBanco();
		} catch (Exception e) {

		}

		return idMesa == 0 ? false : true;
	}

	public boolean adicionarMesa(int idComanda, int idMesa) {

		String sql = "UPDATE COMANDA SET NUMERO_MESA= ? WHERE ID_COMANDA = ? AND STATUS = 1";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idMesa);
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

	public boolean removerMesa(int idComanda) {

		String sql = "UPDATE COMANDA SET NUMERO_MESA = null WHERE ID_COMANDA = ? AND STATUS = 1";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idComanda);
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean alterar(Comanda comanda) {

		String sql = "UPDATE COMANDA SET ID_SERVICO=?, ID_JOGO=?, QUANTIDADE_HORAS=?, ID_FUNCIONARIO=? WHERE ID_CLIENTE = ? AND STATUS = 1";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, comanda.getIdServico());
			pst.setInt(2, comanda.getIdJogo());
			pst.setInt(3, comanda.getQuantidadeHoras());
			pst.setInt(4, comanda.getIdFuncionario());
			pst.setInt(5, comanda.getIdCliente());
			pst.execute();
			pst.close();

			desconectaBanco();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean encerrarComanda(int idComanda) {
		String sql = "UPDATE COMANDA SET STATUS = 0, NUMERO_MESA = null WHERE ID_COMANDA = ? AND STATUS = 1";
		String sql1 = "UPDATE MESA SET STATUS = 0, ID_COMANDA = null WHERE ID_COMANDA = ? AND STATUS = 1";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idComanda);
			pst.execute();
			pst.close();
			
			pst = conn.prepareStatement(sql1);
			pst.setInt(1, idComanda);
			pst.execute();
			pst.close();
			
			desconectaBanco();
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public int gerarNumeroComanda() throws Exception {
		String sql = "SELECT MAX(ID_comanda) AS ID_COMANDA FROM COMANDA";
		int id = 0;

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {

				id += rs.getInt("ID_COMANDA") == 0 ? 1 : rs.getInt("ID_COMANDA") + 1;

			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}

		return id;
	}

	public Comanda procurarId(int id_comanda) throws Exception {
		String sql = "SELECT * FROM COMANDA WHERE ID_COMANDA = ? AND STATUS = 1";
		Comanda comanda = new Comanda();
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_comanda);
			rs = pst.executeQuery();
			while (rs.next()) {

				comanda.setIdComanda(rs.getInt("ID_COMANDA"));
				comanda.setIdCliente(rs.getInt("ID_CLIENTE"));
				comanda.setIdServico(rs.getInt("ID_SERVICO"));
				comanda.setIdJogo(rs.getInt("ID_JOGO"));
				comanda.setQuantidadeHoras(rs.getInt("QUANTIDADE_HORAS"));
				comanda.setDataComanda(rs.getString("DATA_COMANDA"));
				comanda.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				comanda.setNumeroMesa(rs.getInt("NUMERO_MESA"));

			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}

		return comanda;
	}

	public List<Comanda> mostrarTodos() throws Exception {
		List<Comanda> lista = new ArrayList<Comanda>();
		String sql = "SELECT * FROM COMANDA WHERE STATUS = 1";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Comanda comanda = new Comanda();

				comanda.setIdComanda(rs.getInt("ID_COMANDA"));
				comanda.setIdCliente(rs.getInt("ID_CLIENTE"));
				comanda.setIdServico(rs.getInt("ID_SERVICO"));
				comanda.setIdJogo(rs.getInt("ID_JOGO"));
				comanda.setQuantidadeHoras(rs.getInt("QUANTIDADE_HORAS"));
				comanda.setDataComanda(rs.getString("DATA_COMANDA"));
				comanda.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				comanda.setNumeroMesa(rs.getInt("NUMERO_MESA"));

				lista.add(comanda);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;
	}

	public Comanda buscarComandaPorCpf(String cpf) {
		String sql = "SELECT * FROM COMANDA AS CO INNER JOIN CLIENTE AS C ON C.ID_CLIENTE = CO.ID_CLIENTE WHERE C.CPF = ? AND CO.STATUS = 1";
		Comanda comanda = null;
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cpf);
			rs = pst.executeQuery();

			while (rs.next()) {
				comanda = new Comanda();
				comanda.setIdComanda(rs.getInt("ID_COMANDA"));
				comanda.setIdCliente(rs.getInt("ID_CLIENTE"));
				comanda.setIdServico(rs.getInt("ID_SERVICO"));
				comanda.setIdJogo(rs.getInt("ID_JOGO"));
				comanda.setQuantidadeHoras(rs.getInt("QUANTIDADE_HORAS"));
				comanda.setDataComanda(rs.getString("DATA_COMANDA"));
				comanda.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				comanda.setNumeroMesa(rs.getInt("NUMERO_MESA"));
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
			System.out.println(e);
		}

		return comanda;
	}

}
