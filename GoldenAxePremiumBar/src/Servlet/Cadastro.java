package Servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Cadastro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String menu = request.getParameter("menu");

		if (menu != null) {

			switch (menu) {

			case "CadastroCliente":
				try {

					String nome = request.getParameter("nome");
					String cpf = request.getParameter("cpf");
					String email = request.getParameter("email");
					boolean sexo = Utilities.ConvertToBoolean(request.getParameter("sexo"));
					String telefone = request.getParameter("telefone");
					String dataNascimento = request.getParameter("dataNascimento");

					Cliente cliente = new Cliente(nome, cpf, email, sexo, dataNascimento, telefone);
					boolean ret = cadastrarCliente(cliente);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}

				break;

			case "BuscarCliente":

				try {

					String cpf = request.getParameter("cpf");
					Cliente cliente = buscarCliente(cpf);
					String serialize = cliente != null ? Utilities.SerializeClienteToJson(cliente) : "";
					response.getWriter().print(serialize);

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			case "BuscarFornecedor":

				try {

					String cnpj = request.getParameter("cnpj");
					Fornecedor fornecedor = buscarFornecedor(cnpj);
					String serialize = fornecedor != null ? Utilities.SerializeFornecedorToJson(fornecedor) : "";
					response.getWriter().print(serialize);

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			case "CadastroEstoque":
				try {

					int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
					int idProduto = Integer.parseInt(request.getParameter("idProduto"));
					int idJogo = Integer.parseInt(request.getParameter("idJogo"));
					int quantidade = Integer.parseInt(request.getParameter("quantidade"));
					float valor = (float) Float.parseFloat(request.getParameter("valor"));
					int idUnidade = Integer.parseInt(request.getParameter("idUnidade"));

					Estoque estoque = new Estoque(idFornecedor, idProduto, idJogo, quantidade, valor, idUnidade);

					boolean ret = cadastrarEstoque(estoque);

					response.getWriter().print(ret);

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "CadastroFornecedor":
				try {

					String cnpj = request.getParameter("cnpj");
					String nome = request.getParameter("fornecedor");
					String telefone = request.getParameter("telefone");
					String email = request.getParameter("email");

					Fornecedor fornecedor = new Fornecedor(cnpj, nome, telefone, email);
					boolean ret = cadastrarFornecedor(fornecedor);

					response.getWriter().print(ret);

				} catch (Exception e) {

				}

				break;

			case "CadastroFuncionario":

				String nome = request.getParameter("nome");
				String cpf = request.getParameter("cpf");
				boolean sexo = Utilities.ConvertToBoolean(request.getParameter("sexo"));
				int grupo = Integer.parseInt(request.getParameter("grupo"));
				int unidade = Integer.parseInt(request.getParameter("unidade"));
				Funcionario funcionario = new Funcionario(nome, cpf, sexo, grupo, unidade);
				int idFuncionario = cadastrarFuncionario(funcionario);

				String user = request.getParameter("usuario");
				String pswd = request.getParameter("pswd");
				if (idFuncionario != 0) {
					Usuario usuario = new Usuario(user, pswd, idFuncionario);
					boolean ret = cadastrarUsuario(usuario);
					response.getWriter().print(ret);
				} else {
					response.getWriter().print(false);
				}
				break;

			case "CadastroItem":

				try {
					float valor = Float.parseFloat(request.getParameter("valor"));
					int tipo = Integer.parseInt(request.getParameter("tipo"));
					int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
					String nome1 = request.getParameter("nome");
					int quantidade = Integer.parseInt(request.getParameter("quantidade"));
					int idItem = Integer.parseInt(request.getParameter("idItem"));
					boolean isChecked = Boolean.parseBoolean(request.getParameter("isChecked"));
					if (isChecked) {
						if (tipo == 2) {

							Jogos jogos = new Jogos(nome1, quantidade, valor, idFornecedor);
							int idJogo = cadastrarJogos(jogos);

							if (idJogo != 0) {
								Estoque estoque = new Estoque(idFornecedor, 0, idJogo, quantidade, valor, 1);
								boolean ret = adicionarItemEstoque(estoque);
								response.getWriter().print(ret);
							} else {
								response.getWriter().print(false);
							}

						} else if (tipo == 1) {

							Produto produto = new Produto(nome1, quantidade, valor, idFornecedor);
							int idProduto = cadastrarProduto(produto);

							if (idProduto != 0) {
								Estoque estoque = new Estoque(idFornecedor, idProduto, 0, quantidade, valor, 1);
								boolean ret = adicionarItemEstoque(estoque);
								response.getWriter().print(ret);
							} else {
								response.getWriter().print(false);
							}
						}
					} else {

						boolean ret = adicionarItemCadastradoEstoque(idItem, quantidade, valor, tipo);
						response.getWriter().print(ret);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			case "CadastroServico":
				try {

					String descricao = request.getParameter("descricao");
					float valorHora = (float) Float.parseFloat("valorHora");

					Servico servico = new Servico(descricao, valorHora);

					boolean ret = cadastrarServico(servico);

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
		String menu = request.getParameter("menu");

		if (menu != null) {

			switch (menu) {

			case "CadastroFuncionario":

				try {
					String nome = request.getParameter("nome");
					String cpf = request.getParameter("cpf");
					boolean sexo = Utilities.ConvertToBoolean(request.getParameter("sexo"));
					int grupo = Integer.parseInt(request.getParameter("grupo"));
					int unidade = Integer.parseInt(request.getParameter("unidade"));
					Funcionario funcionario = new Funcionario(nome, cpf, sexo, grupo, unidade);
					int idFuncionario = cadastrarFuncionario(funcionario);

					String user = request.getParameter("usuario");
					String pswd = request.getParameter("pswd");

					MessageDigest crypt;
					crypt = MessageDigest.getInstance("MD5");
					byte pswdByte[] = crypt.digest(pswd.getBytes("UTF-8"));
					
					StringBuilder hexString = new StringBuilder();
					for (byte b : pswdByte) {
					  hexString.append(String.format("%02X", 0xFF & b));
					}
					pswd = hexString.toString();
					
					if (idFuncionario != 0) {
						Usuario usuario = new Usuario(user, pswd, idFuncionario);
						boolean ret = cadastrarUsuario(usuario);
						response.getWriter().print(ret);
					} else {
						response.getWriter().print(false);
					}
					
				} catch (NoSuchAlgorithmException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}

				break;

			case "BuscarFuncionario":
				try {

					String cpfFunc = request.getParameter("cpf");
					Funcionario funcionario1 = buscarFuncionarioCpf(cpfFunc);
					Usuario usuario = buscarUsuarioPorIdFuncionario(funcionario1.getId());
					String serialize = funcionario1 != null
							? Utilities.SerializeFuncionarioToJson(funcionario1, usuario) : "";
					response.getWriter().print(serialize);

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			}
		}
	}

	public boolean cadastrarCliente(Cliente cliente) {

		DaoCliente cDAL = new DaoCliente();
		return cDAL.salvar(cliente);
	}

	public static Cliente buscarCliente(String cpf) throws Exception {

		DaoCliente cDAL = new DaoCliente();
		Cliente cliente = cDAL.buscarClienteCPF(cpf);
		return cliente;
	}

	public Fornecedor buscarFornecedor(String cnpj) throws Exception {

		DaoFornecedor cDAL = new DaoFornecedor();
		return cDAL.buscarFornecedorPorCNPJ(cnpj);
	}

	public Funcionario buscarFuncionarioCpf(String cpf) throws Exception {

		DaoFuncionario cDAL = new DaoFuncionario();
		Funcionario funcionario = cDAL.buscarFuncionarioCPF(cpf);
		return funcionario;
	}

	public boolean cadastrarEstoque(Estoque estoque) {

		DaoEstoque eDAL = new DaoEstoque();
		return eDAL.salvar(estoque);
	}

	public boolean cadastrarFornecedor(Fornecedor fornecedor) {

		DaoFornecedor fDAL = new DaoFornecedor();
		return fDAL.salvar(fornecedor);
	}

	public int cadastrarFuncionario(Funcionario funcionario) {

		DaoFuncionario cDAL = new DaoFuncionario();
		return cDAL.salvar(funcionario);

	}

	public int cadastrarJogos(Jogos jogos) {

		DaoJogos jDAL = new DaoJogos();
		return jDAL.salvar(jogos);
	}

	public int cadastrarProduto(Produto produto) {
		DaoProduto pDAL = new DaoProduto();
		return pDAL.salvar(produto);
	}

	public boolean cadastrarServico(Servico servico) {

		DaoServicos sDAL = new DaoServicos();
		return sDAL.salvar(servico);
	}

	public boolean cadastrarUnidade(Unidade unidade) {

		DaoUnidade uDAL = new DaoUnidade();
		return uDAL.salvar(unidade);
	}

	public boolean cadastrarUsuario(Usuario usuario) {

		DaoUsuarios cDAL = new DaoUsuarios();
		return cDAL.salvar(usuario);
	}

	public Usuario buscarUsuarioPorIdFuncionario(int idFuncionario) {
		DaoUsuarios cDAL = new DaoUsuarios();
		return cDAL.buscarUsuario(idFuncionario);
	}

	public boolean adicionarItemEstoque(Estoque estoque) {
		DaoEstoque daoEstoque = new DaoEstoque();
		return daoEstoque.salvar(estoque);
	}

	public boolean adicionarItemCadastradoEstoque(int idItem, int quantidade, float valor, int tipo) {
		DaoEstoque daoEstoque = new DaoEstoque();
		return daoEstoque.updateItemCadastrado(idItem, quantidade, valor, tipo);
	}

}
