package Servlet;

import java.io.IOException;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoCliente;
import DAO.DaoComanda;
import DAO.DaoConsumo;
import DAO.DaoJogos;
import DAO.DaoMesa;
import DAO.DaoProduto;
import Entity.Cliente;
import Entity.Consumo;
import Entity.ConsumoComandaResponseEntity;
import Entity.Jogos;
import Entity.Produto;
import Utilities.Utilities;

public class Comanda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Comanda() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String menu = request.getParameter("menu");
		Gson gson = new Gson();

		if (menu != null) {

			switch (menu) {

			case "AbrirComanda":
				try {
					response.getWriter().print(gerarComanda());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "CarregarJogos":
				try {
					List<Jogos> lista = listarJogos();
					String serialize = Utilities.SerializeJogosToJson(lista);
					response.getWriter().print(serialize);

				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}
				break;
				
			case "CarregarProdutos":
				try {
					List<Produto> lista = listarProdutos();
					String serialize = gson.toJson(lista);
					response.getWriter().print(serialize);

				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}
				break;
				

			case "CarregarMesas":

				try {
					List<Mesa> listaMesa = listarMesas();
					String serialize = gson.toJson(listaMesa);
					response.getWriter().printf(serialize);

				} catch (Exception e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}

				break;

			case "CadastrarComanda":
				try {

					String cpf = request.getParameter("cpf");
					int idComanda = Integer.parseInt(request.getParameter("numComanda"));
					int idCliente = buscarIdClienteCpf(cpf);
					int idServico = Integer.parseInt(request.getParameter("servico"));
					int idJogo = Integer.parseInt(request.getParameter("jogo"));
					String qtdHoras = request.getParameter("horas");
					int idFuncionario = 1;
					String dataComanda = request.getParameter("data");
					String produtos = request.getParameter("produtos") == null ? "vazio"
							: request.getParameter("produtos");

					Entity.Comanda comanda = new Entity.Comanda(idCliente, idServico, idJogo, qtdHoras, dataComanda,
							idFuncionario);

					if (!produtos.equalsIgnoreCase("vazio")) {
						Consumo[] listaProdutos = gson.fromJson(produtos, Consumo[].class);
						for (Consumo produto : listaProdutos) {
							salvarConsumo(new Consumo(produto.getIdProduto(), idComanda, produto.getQuantidade()));
						}
					}

					boolean ret = gravarComanda(comanda);
					response.getWriter().print(ret);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "AtualizarComanda":
				try {

					String cpf = request.getParameter("cpf");
					int idComanda = Integer.parseInt(request.getParameter("numComanda"));
					int idCliente = buscarIdClienteCpf(cpf);
					int idServico = Integer.parseInt(request.getParameter("servico"));
					int idJogo = Integer.parseInt(request.getParameter("jogo"));
					String qtdHoras = request.getParameter("horas");
					int idFuncionario = 1;
					String produtos = request.getParameter("produtos") == null ? "vazio"
							: request.getParameter("produtos");

					if (!produtos.equalsIgnoreCase("vazio")) {
						Consumo[] listaProdutos = gson.fromJson(produtos, Consumo[].class);
						for (Consumo produto : listaProdutos) {
							salvarConsumo(new Consumo(produto.getIdProduto(), idComanda, produto.getQuantidade()));
						}
					}

					Entity.Comanda comanda = new Entity.Comanda(idCliente, idServico, idJogo, qtdHoras, idFuncionario);

					boolean ret = atualizarComanda(comanda);
					response.getWriter().print(ret);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "VerificaProdutoCadastrado":

				String produto = request.getParameter("produto");

				try {
					Produto product = buscarProduto(produto);
					String serialize = Utilities.SerializeProdutoToJson(product);
					response.getWriter().print(serialize);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "BuscarComandaCliente":

				int idComanda = Integer.parseInt(request.getParameter("idComanda"));
				try {
					ArrayList<ConsumoComandaResponseEntity> lista = buscarConsumoPorIdComanda(idComanda);
					
					if(lista.size() > 0){
						String listaSerialize = gson.toJson(lista);
						response.getWriter().print(listaSerialize);
					}else{
						response.getWriter().print("");
					}

				} catch (Exception e) {

					System.out.println(e);
					e.printStackTrace();
				}
				break;

			case "BuscarIdComandaNomeCliente":
				try {
					String cpf = request.getParameter("cpf");
					Cliente cliente = Servlet.Cadastro.buscarCliente(cpf);
					Entity.Comanda comanda = buscarComandaPorCpf(cpf);

					String serialize = Utilities.SerializeIdComandaClienteNome(cliente, comanda);

					response.getWriter().print(serialize);

				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case "AlterarItemConsumo":

				int idItemConsumo = Integer.parseInt(request.getParameter("idItemConsumo"));
				int idComandaConsumo = Integer.parseInt(request.getParameter("idComanda"));
				int quantidadeConsumo = Integer.parseInt(request.getParameter("quantidade"));
				int idProduto = Integer.parseInt(request.getParameter("produto"));

				try {
					boolean ret = alterarItemComanda(
							new Consumo(idItemConsumo, idProduto, idComandaConsumo, quantidadeConsumo));
					response.getWriter().print(ret);

				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case "ExcluirItemConsumo":

				int itemConsumo = Integer.parseInt(request.getParameter("idItemConsumo"));
				int comandaConsumo = Integer.parseInt(request.getParameter("idComanda"));
				try {
					boolean ret = removerItemComanda(itemConsumo, comandaConsumo);
					response.getWriter().print(ret);
				} catch (Exception e) {
					System.out.println(e);
				}
				break;

			case "FecharComanda":

				try {
					int idComanda1 = Integer.parseInt(request.getParameter("numComanda"));
					boolean ret = encerrarComanda(idComanda1);
					response.getWriter().print(ret);
				} catch (Exception e) {
					System.out.println(e);
				}
				break;

			case "AssociarMesaComanda":

				try {

					int idMesa = Integer.parseInt(request.getParameter("idMesa"));
					int idComanda1 = Integer.parseInt(request.getParameter("idComanda"));
					boolean ret = associarMesaAcomanda(idComanda1, idMesa);
					boolean ret2 = associarComandaAmesa(idComanda1, idMesa);
					if (ret && ret2)
						response.getWriter().print(true);
					else
						response.getWriter().print(false);

				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case "RemoverMesaComanda":
				try {

					int idComanda1 = Integer.parseInt(request.getParameter("idComanda"));
					boolean ret = removerMesaDeComanda(idComanda1);
					boolean ret2 = removerComandaMesa(idComanda1);
					if (ret && ret2)
						response.getWriter().print(true);
					else
						response.getWriter().print(false);

				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case "VerificarClientePossuiMesa":

				try {
					int idComanda1 = Integer.parseInt(request.getParameter("idComanda"));
					boolean ret = clientePossuiComanda(idComanda1);
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

	public int buscarIdClienteCpf(String cpf) throws Exception {
		DaoCliente cDAL = new DaoCliente();
		return cDAL.buscarIdClientePorCPF(cpf);
	}

	public int gerarComanda() throws Exception {
		DaoComanda cDAL = new DaoComanda();
		return cDAL.gerarNumeroComanda();
	}

	public List<Jogos> listarJogos() throws Exception {

		DaoJogos cDAL = new DaoJogos();
		return cDAL.mostrarTodos();
	}
	
	public List<Produto> listarProdutos() throws Exception {

		DaoProduto daoProduto = new DaoProduto();
		return daoProduto.mostrarTodos();
	}
	

	public List<Mesa> listarMesas() throws Exception {

		DaoMesa cDAL = new DaoMesa();
		return cDAL.mostrarTodas();
	}

	public boolean associarComandaAmesa(int idComanda, int idMesa) {
		DaoMesa cDAL = new DaoMesa();
		return cDAL.associarComanda(idComanda, idMesa);
	}

	public boolean removerComandaMesa(int idComanda) {
		DaoMesa cDAL = new DaoMesa();
		return cDAL.removerComanda(idComanda);
	}

	public boolean gravarComanda(Entity.Comanda comanda) {

		DaoComanda cDAL = new DaoComanda();
		return cDAL.salvar(comanda);

	}

	public boolean atualizarComanda(Entity.Comanda comanda) {

		DaoComanda cDAL = new DaoComanda();
		return cDAL.alterar(comanda);

	}

	public Produto buscarProduto(String produto) throws Exception {

		DaoProduto daoProduto = new DaoProduto();
		return daoProduto.buscarProduto(produto);

	}

	public boolean salvarConsumo(Consumo consumo) {
		DaoConsumo daoConsumo = new DaoConsumo();
		return daoConsumo.salvar(consumo);

	}

	public Entity.Comanda buscarComandaPorId(int idComanda) throws Exception {
		DaoComanda daoComanda = new DaoComanda();
		return daoComanda.procurarId(idComanda);
	}

	public Entity.Comanda buscarComandaPorCpf(String cpf) throws Exception {
		DaoComanda daoComanda = new DaoComanda();
		return daoComanda.buscarComandaPorCpf(cpf);
	}

	public boolean alterarItemComanda(Consumo consumo) {
		DaoConsumo daoConsumo = new DaoConsumo();
		return daoConsumo.alterar(consumo);
	}

	public boolean removerItemComanda(int idItem, int idComanda) {
		DaoConsumo daoConsumo = new DaoConsumo();
		return daoConsumo.excluir(idItem, idComanda);
	}

	public ArrayList<ConsumoComandaResponseEntity> buscarConsumoPorIdComanda(int id) {
		DaoConsumo daoConsumo = new DaoConsumo();
		return daoConsumo.buscarConsumoIdComanda(id);
	}

	public boolean associarMesaAcomanda(int idComanda, int idMesa) {
		DaoComanda daoComanda = new DaoComanda();
		return daoComanda.adicionarMesa(idComanda, idMesa);
	}

	public boolean removerMesaDeComanda(int idComanda) {
		DaoComanda daoComanda = new DaoComanda();
		return daoComanda.removerMesa(idComanda);
	}

	public boolean clientePossuiComanda(int idComanda) {
		DaoComanda daoComanda = new DaoComanda();
		return daoComanda.clientePossuiMesa(idComanda);
	}
	
	public boolean encerrarComanda(int idComanda){
		DaoComanda daoComanda = new DaoComanda();
		return daoComanda.encerrarComanda(idComanda);
	}

}
