package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DaoCliente;
import DAO.DaoEstoque;
import DAO.DaoFornecedor;
import DAO.DaoFuncionario;
import DAO.DaoUsuarios;

public class Exclusao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Exclusao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String menu = request.getParameter("menu");

		if (menu != null) {

			switch (menu) {

			case "ExcluirCliente":
				try {

					String cpf = request.getParameter("cpf");
					int idCliente = buscarIdCliente(cpf);
					boolean ret = excluirCadastroCliente(idCliente);
					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "ExcluirFuncionario":
				try {

					String cpf = request.getParameter("cpf");
					int idFuncionario = buscarIdFuncionarioPorCpf(cpf);
					boolean ret = excluirFuncionario(idFuncionario);
					boolean retUser = excluirUsuario(idFuncionario);
					response.getWriter().print(ret && retUser == true ? true : false);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "ExcluirFornecedor":
				try {

					String cnpj = request.getParameter("cnpj");
					boolean ret = excluirFornecedor(cnpj);
					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "ExclusaoItemEstoque":
				try {
					int idItem = Integer.parseInt(request.getParameter("idItem"));
					int tipo = Integer.parseInt(request.getParameter("tipo"));
					boolean ret = excluirEntradaItem(idItem, tipo);
					response.getWriter().print(ret);
					
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public int buscarIdCliente(String cpf) throws Exception {
		DaoCliente cDAL = new DaoCliente();
		return cDAL.buscarIdClientePorCPF(cpf);
	}

	public boolean excluirCadastroCliente(int id) {
		DaoCliente cDAL = new DaoCliente();
		return cDAL.deletar(id);
	}

	public int buscarIdFuncionarioPorCpf(String cpf) throws Exception {

		DaoFuncionario cDAL = new DaoFuncionario();
		int ret = cDAL.buscarIdFuncionarioPorCPF(cpf);
		return ret;
	}

	public boolean excluirFuncionario(int idFuncionario) {
		DaoFuncionario cDAL = new DaoFuncionario();

		return cDAL.deletar(idFuncionario);
	}

	public boolean excluirUsuario(int idFuncionario) {
		DaoUsuarios cDAL = new DaoUsuarios();
		return cDAL.deletar(idFuncionario);
	}

	public boolean excluirFornecedor(String cpf) {
		DaoFornecedor cDAL = new DaoFornecedor();
		return cDAL.deletar(cpf);
	}
	
	public boolean excluirEntradaItem(int idItem, int tipo){
		DaoEstoque daoEstoque = new DaoEstoque();
		return daoEstoque.deletar(idItem, tipo);
	}
}