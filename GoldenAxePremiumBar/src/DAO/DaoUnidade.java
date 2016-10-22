package DAO;
import Entity.Unidade;

public class DaoUnidade extends ConnectionDAO {
	
	public void salvar(Unidade unidade) {
		
		String sql = "INSERT INTO UNIDADE (NOME) VALUES (?)";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, unidade.getNome()); 
			pst.execute();
			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void alterar(Unidade unidade){
		String sql = "UPDATE UNIDADE SET NOME=? WHERE ID_UNIDADE = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, unidade.getNome()); 
			pst.execute();
			pst.close();
			
			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void deletar(int id_Unidade){
		String sql = "DELETE FROM UNIDADE WHERE ID_UNIDADE = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Unidade);
			pst.execute();
			pst.close();
			
			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
}
