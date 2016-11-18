package Servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoCliente;
import DAO.DaoComanda;
import DAO.DaoJogos;
import DAO.DaoProduto;
import Entity.Jogos;
import Entity.Produto;
import Utilities.Utilities;
import sun.util.BuddhistCalendar;

public class Comanda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Comanda() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String menu = request.getParameter("menu");

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
					e.printStackTrace();
				}
				break;

			case "CadastrarComanda":
				try {

					String cpf = request.getParameter("cpf");
					int idCliente = buscarIdClienteCpf(cpf);
					int idServico = Integer.parseInt(request.getParameter("servico"));
					int idJogo = Integer.parseInt(request.getParameter("jogo"));
					int qtdHoras = Integer.parseInt(request.getParameter("horas"));
					int idFuncionario = 1;
					String dataComanda = request.getParameter("data");

					Entity.Comanda comanda = new Entity.Comanda(idCliente, idServico, idJogo, qtdHoras, dataComanda,
							idFuncionario);
					
					boolean ret = gravarComanda(comanda);

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
	
	public boolean gravarComanda(Entity.Comanda comanda){
		
		DaoComanda cDAL = new DaoComanda();
		return cDAL.salvar(comanda);
		
	}
	
public Produto buscarProduto(String produto) throws Exception{
		
		DaoProduto daoProduto = new DaoProduto();
		return daoProduto.buscarProduto(produto);
		
	}

}
