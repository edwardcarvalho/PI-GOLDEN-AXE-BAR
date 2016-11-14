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
import Utilities.*;

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

//			case "CadastroFuncionario":
//
//				String nome = request.getParameter("nome");
//				String cpf = request.getParameter("cpf");
//				boolean sexo = Utilities.ConvertToBoolean(request.getParameter("sexo"));
//				int grupo = Integer.parseInt(request.getParameter("grupo"));
//				int unidade = Integer.parseInt(request.getParameter("unidade"));
//				Funcionario funcionario = new Funcionario(nome, cpf, sexo, grupo, unidade);
//				int idFuncionario = cadastrarFuncionario(funcionario);
//
//				String user = request.getParameter("usuario");
//				String pswd = request.getParameter("pswd");
//				if (idFuncionario != 0) {
//					Usuario usuario = new Usuario(user, pswd, idFuncionario);
//					boolean ret = cadastrarUsuario(usuario);
//					response.getWriter().print(ret);
//				}else{
//					response.getWriter().print(false);
//				}
//				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");

		if (menu != null) {

			switch (menu) {

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
				}else{
					response.getWriter().print(false);
				}
				break;
				
			case "BuscarFuncionario":
				try {

					String cpfFunc = request.getParameter("cpf");
					Funcionario funcionario1 = buscarFuncionarioCpf(cpfFunc);
					Usuario usuario = buscarUsuarioPorIdFuncionario(funcionario1.getId());
					String serialize = funcionario1 != null ? Utilities.SerializeFuncionarioToJson(funcionario1,usuario) : "";
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

	public Cliente buscarCliente(String cpf) throws Exception {

		DaoCliente cDAL = new DaoCliente();
		Cliente cliente = cDAL.buscarClienteCPF(cpf);
		return cliente;
	}
	
	public Funcionario buscarFuncionarioCpf(String cpf) throws Exception {

		DaoFuncionario cDAL = new DaoFuncionario();
		Funcionario funcionario = cDAL.buscarFuncionarioCPF(cpf);
		return funcionario;
	}

	public int cadastrarFuncionario(Funcionario funcionario) {

		DaoFuncionario cDAL = new DaoFuncionario();
		return cDAL.salvar(funcionario);

	}

	public boolean cadastrarUsuario(Usuario usuario) {
		DaoUsuarios cDAL = new DaoUsuarios();
		return cDAL.salvar(usuario);
	}
	
	public Usuario buscarUsuarioPorIdFuncionario(int idFuncionario){
		DaoUsuarios cDAL = new DaoUsuarios();
		return cDAL.buscarUsuario(idFuncionario);
	}

}
