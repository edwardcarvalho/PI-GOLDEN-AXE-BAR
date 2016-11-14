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
import DAO.DaoJogos;
import DAO.DaoProduto;
import DAO.DaoServicos;
import DAO.DaoUnidade;
import DAO.DaoUsuarios;
import Entity.Cliente;
import Entity.Estoque;
import Entity.Fornecedor;
import Entity.Funcionario;
import Entity.Jogos;
import Entity.Produto;
import Entity.Servico;
import Entity.Unidade;
import Entity.Usuario;
import Utilities.Utilities;

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

			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public int buscarIdCliente(String cpf) throws Exception {
		DaoCliente cDAL = new DaoCliente();
		return cDAL.buscarIdClientePorCPF(cpf);
	}

	public boolean excluirCadastroCliente(int id) {
		DaoCliente cDAL = new DaoCliente();
		return cDAL.deletar(id);
	}

}
