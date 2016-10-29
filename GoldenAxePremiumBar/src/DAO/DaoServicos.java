package DAO;

import java.util.ArrayList;
import java.util.List;

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
		}
	}

	public void deletar(int id_Servico) {
		String sql = "DELETE FROM SERVICO WHERE ID_SERVICO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Servico);
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
		}
	}
	public Servico procurarId(int id_servico) throws Exception {
		String sql = "SELECT * FROM SERVICO WHERE ID_SERVICO = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_servico);
			rs = pst.executeQuery();
		} catch (Exception e) {
		}
		Servico servico = new Servico();
		while (rs.next()) {

			servico.setDescricao(rs.getString("DESCRICAO"));
			servico.setValorHora(rs.getFloat("VALOR_HORA"));
		}
		pst.close();
		desconectaBanco();

		return servico;
	}


	public List<Servico> procurarNome(String descricao) throws Exception {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * FROM SERVICO WHERE DESCRICAO LIKE '%" + descricao + "%'";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, descricao);
			rs = pst.executeQuery();

			while (rs.next()) {
				Servico servico = new Servico();

				servico.setDescricao(rs.getString("DESCRICAO"));
				servico.setValorHora(rs.getFloat("VALOR_HORA"));
				lista.add(servico);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}
	public List<Servico> mostrarTodos() throws Exception {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * FROM SERVICO";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Servico servico = new Servico();
				
				servico.setDescricao(rs.getString("DESCRICAO"));
				servico.setValorHora(rs.getFloat("VALOR_HORA"));
				
				lista.add(servico);
			}
			pst.close();
			desconectaBanco();

		} catch (Exception e) {
		}
		return lista;
	}

}
