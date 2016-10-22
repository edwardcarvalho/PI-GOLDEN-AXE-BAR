package DAO;

import Entity.Produto;

public class DaoProduto extends ConnectionDAO{
	public void salvar(Produto produto) {

		String sql = "INSERT INTO PRODUTO(NOME, QUANTIDADE,VALOR, ID_FORNECEDOR) VALUES(?,?,?,?)";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setInt(2, produto.getQuantidade());
			pst.setFloat(3, produto.getValor());
			pst.setInt(4, produto.getIdFornecedor());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void alterar(Usuarios usuarios) {

		String sql = "UPDATE USUARIO SET ID_FUNCIONARIO = ?, NOME_USU = ?, SENHA = ? WHERE ID_USUARIO = ?";

		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, usuarios.getId_Funcionario());
			pst.setString(2, usuarios.getUsuario());
			pst.setString(3, usuarios.getSenha());
			pst.execute();

			pst.close();

			desconectaBanco();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(Integer id_Usuario) {
		String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
		try {
			conectaBanco();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Usuario);
			pst.execute();
			pst.close();

			desconectaBanco();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
