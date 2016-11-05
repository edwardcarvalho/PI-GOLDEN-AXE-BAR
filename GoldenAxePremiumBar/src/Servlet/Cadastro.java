package Servlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DaoCliente;
import Entity.Cliente;
import Utilities.*;
import jdk.nashorn.internal.ir.debug.JSONWriter;

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
					boolean cadastrar = cadastrarCliente(cliente);
					response.getWriter().print(cadastrar);
				
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
	
	public boolean cadastrarCliente(Cliente cliente){
		
		DaoCliente cDAL = new DaoCliente();
		return cDAL.salvar(cliente);
	}

}
