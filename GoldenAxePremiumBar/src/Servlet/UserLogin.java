package Servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoUsuarios;
import Entity.Usuario;

public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String user = request.getParameter("user");
			String psw = request.getParameter("psw");

			MessageDigest crypt;
			crypt = MessageDigest.getInstance("MD5");
			byte pswdByte[] = crypt.digest(psw.getBytes("UTF-8"));
			
			StringBuilder hexString = new StringBuilder();
			for (byte b : pswdByte) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			psw = hexString.toString();

			Usuario usuario = checkUser(user, psw);

			if (usuario != null) {
				addCookie(response, "usuarioAutenticado", user, -1);
				addCookie(response, "grupoUsuario", Integer.toString(usuario.getIdGrupoFuncionario()), -1);
				response.getWriter().print(true);
			} else {
				response.getWriter().print(false);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public Usuario checkUser(String user, String psw) {
		DaoUsuarios daoUsuarios = new DaoUsuarios();
		return daoUsuarios.checarUsuario(user, psw);
	}

}
