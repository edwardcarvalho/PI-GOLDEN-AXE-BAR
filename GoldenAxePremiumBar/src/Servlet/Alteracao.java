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

			case "AlterarEstoque":
				try {

					int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
					int idProduto = Integer.parseInt(request.getParameter("idProduto"));
					int idJogo = Integer.parseInt(request.getParameter("idJogo"));
					int quantidade = Integer.parseInt(request.getParameter("quantidade"));
					float valor = (float) Float.parseFloat(request.getParameter("valor"));
					int idUnidade = Integer.parseInt(request.getParameter("idUnidade"));

					Estoque estoque = new Estoque(idFornecedor, idProduto, idJogo, quantidade, valor, idUnidade);

					boolean ret = alterarDadosEstoque(estoque);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}

			case "AlterarForncedor":
				try {

					String cnpj = request.getParameter("cnpj");
					String nome = request.getParameter("nome");
					String telefone = request.getParameter("telefone");
					String email = request.getParameter("email");

					Fornecedor fornecedor = new Fornecedor(cnpj, nome, telefone, email);
					boolean ret = alterarDadosFornecedor(fornecedor);

					response.getWriter().print(ret);

				} catch (Exception e) {

				}

				break;

			case "AlterarFuncionario":
				try {
					String nome = request.getParameter("nome");
					String cpf = request.getParameter("cpf");
					boolean sexo = Utilities.ConvertToBoolean(request.getParameter("sexo"));
					int grupo = Integer.parseInt(request.getParameter("grupo"));
					int unidade = Integer.parseInt(request.getParameter("unidade"));

					Funcionario funcionario = new Funcionario(nome, cpf, sexo, grupo, unidade);

					boolean ret = alterarDadosFuncionario(funcionario);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "AlterarJogos":
				try {

					String nome = request.getParameter("nome");
					int quantidade = Integer.parseInt(request.getParameter("quantidade"));
					float valor = (float) Float.parseFloat(request.getParameter("valor"));
					int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));

					Jogos jogos = new Jogos(nome, quantidade, valor, idFornecedor);

					boolean ret = alterarDadosJogos(jogos);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "alterarProduto":
				try {

					String nome = request.getParameter("nome");
					int quantidade = Integer.parseInt(request.getParameter("quantidade"));
					float valor = (float) Float.parseFloat(request.getParameter("valor"));
					int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));

					Produto produto = new Produto(nome, quantidade, valor, idFornecedor);

					boolean ret = alterarDadosProduto(produto);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "AlterarServico":
				try {

					String descricao = request.getParameter("descricao");
					float valorHora = (float) Float.parseFloat("valorHora");

					Servico servico = new Servico(descricao, valorHora);

					boolean ret = alterarDadosServico(servico);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "AlterarUnidade":
				try {

					String nome = request.getParameter("nome");

					Unidade unidade = new Unidade(nome);

					boolean ret = alterarDadosUnidade(unidade);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "AlterarUsuario":
				try {

					String usuario = request.getParameter("usuario");
					String senha = request.getParameter("senha");
					int idFuncionario = Integer.parseInt("idFuncionario");

					Usuario user = new Usuario(usuario, senha, idFuncionario);

					boolean ret = alterarDadosUsuario(user);

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

	public boolean alterarDadosFuncionario(Funcionario funcionario) {

		DaoFuncionario funDAL = new DaoFuncionario();

		boolean ret = funDAL.alterar(funcionario);

		return ret;

	}

	public boolean alterarDadosEstoque(Estoque estoque) {

		DaoEstoque eDAL = new DaoEstoque();

		boolean ret = eDAL.alterar(estoque);

		return ret;

	}

	public boolean alterarDadosJogos(Jogos jogos) {

		DaoJogos jDAL = new DaoJogos();

		boolean ret = jDAL.alterar(jogos);

		return ret;

	}

	public boolean alterarDadosProduto(Produto produto) {

		DaoProduto pDAL = new DaoProduto();

		boolean ret = pDAL.alterar(produto);

		return ret;

	}

	public boolean alterarDadosServico(Servico servico) {

		DaoServicos sDAL = new DaoServicos();

		boolean ret = sDAL.alterar(servico);

		return ret;

	}

	public boolean alterarDadosUnidade(Unidade unidade) {

		DaoUnidade uDAL = new DaoUnidade();

		boolean ret = uDAL.alterar(unidade);

		return ret;
	}

	public boolean alterarDadosUsuario(Usuario usuario) {

		DaoUsuarios usuDAL = new DaoUsuarios();

		boolean ret = usuDAL.alterar(usuario);

		return ret;

	}
}
