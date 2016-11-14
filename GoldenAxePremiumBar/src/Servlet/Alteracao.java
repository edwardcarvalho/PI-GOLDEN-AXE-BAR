package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoCliente;
import DAO.DaoFuncionario;
import DAO.DaoUsuarios;
import Entity.Cliente;
import Entity.Funcionario;
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
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");

		if (menu != null) {

			switch (menu) {

			case "AlterarFuncionario":
				try {

					String nome = request.getParameter("nome");
					String cpf = request.getParameter("cpf");
					int idFuncionario = buscarIdFuncionarioPorCpf(cpf);
					boolean sexo = Utilities.ConvertToBoolean(request.getParameter("sexo"));
					int grupo = Integer.parseInt(request.getParameter("grupo"));
					int unidade = Integer.parseInt(request.getParameter("unidade"));
					String usuario = request.getParameter("usuario");
					String pswd = request.getParameter("pswd");
					Funcionario funcionario = new Funcionario(nome,cpf,sexo,grupo,unidade);
					Usuario user = new Usuario(usuario,pswd,idFuncionario);
					boolean retFunc = alterarDadosFuncionario(funcionario);
					boolean retUser = alterarDadosUsuario(user);
					response.getWriter().print((retFunc && retUser) ? true : false);

				} catch (Exception e) {
					// TODO: handle exception
				}

				break;
			}
		}
	}

	public boolean alterarDadosCliente(Cliente cliente) {

		DaoCliente cDAL = new DaoCliente();

		boolean ret = cDAL.alterar(cliente);

		return ret;
	}

	public boolean alterarDadosFuncionario(Funcionario funcionario) {

		DaoFuncionario cDAL = new DaoFuncionario();

		boolean ret = cDAL.alterar(funcionario);

		return ret;
	}
	
	public boolean alterarDadosUsuario(Usuario user) {

		DaoUsuarios cDAL = new DaoUsuarios();

		boolean ret = cDAL.alterar(user);

		return ret;
	}

	public int buscarIdFuncionarioPorCpf(String cpf) throws Exception {

		DaoFuncionario cDAL = new DaoFuncionario();

		int ret = cDAL.buscarIdFuncionarioPorCPF(cpf);

		return ret;
	}
}
