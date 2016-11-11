package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoCliente;
import DAO.DaoEstoque;
import DAO.DaoFornecedor;
import Entity.Cliente;
import Entity.Estoque;
import Entity.Fornecedor;
import Utilities.Utilities;

public class Alteracao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Alteracao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String menu = request.getParameter("menu");

		if (menu != null) {

			switch (menu) {

			case "AlterarCliente":
				try {

					String nome = request.getParameter("nome");
					String cpf = request.getParameter("cpf");
					String email = request.getParameter("email");
					boolean sexo = Utilities.ConvertToBoolean(request.getParameter("sexo"));
					String telefone = request.getParameter("telefone");
					String dataNascimento = request.getParameter("dataNascimento");

					Cliente cliente = new Cliente(nome, cpf, email, sexo, dataNascimento, telefone);
					boolean ret = alterarDadosCliente(cliente);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}

				break;

			case "AlterarForncedor":
				try {
					
					String cnpj = request.getParameter("cnpj");
					String nome = request.getParameter("nome");
					String telefone = request.getParameter("telefone");
					String email = request.getParameter("email");
					
					Fornecedor fornecedor = new Fornecedor(cnpj,nome, telefone, email);
					boolean ret = alterarDadosFornecedor(fornecedor);
					
					response.getWriter().print(ret);					

				} catch (Exception e) {

				}

				break;

			case "AlterarEstoque":
				try {

					String idFornecedor = request.getParameter("idFornecedor");
					String idProduto = request.getParameter("idProduto");
					String idJogo = request.getParameter("idJogo");
					String quantidade = request.getParameter("quantidade");
					String valor = request.getParameter("valor");
					String idUnidade = request.getParameter("idUnidade");
					
					Estoque estoque = new Estoque(idFornecedor, idProduto, idJogo, quantidade, valor, idUnidade);
					boolean ret = alterarDadosEstoque(estoque);
					
					response.getWriter().print(ret);
							
							
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public boolean alterarDadosCliente(Cliente cliente) {

		DaoCliente cDAL = new DaoCliente();

		boolean ret = cDAL.alterar(cliente);

		return ret;
	}

	public boolean alterarDadosFornecedor(Fornecedor fornecedor) {

		DaoFornecedor fDAL = new DaoFornecedor();

		boolean ret = fDAL.alterar(fornecedor);

		return ret;

	}

	public boolean alterarDadosEstoque(Estoque estoque) {

		DaoEstoque eDAL = new DaoEstoque();

		boolean ret = eDAL.alterar(estoque);

		return ret;

	}

}
