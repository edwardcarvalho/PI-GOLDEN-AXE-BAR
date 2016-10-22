package Entity;


public class Usuarios {

	private int IdFuncionario;
	private String Usuario;
	private String Senha;

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		this.Usuario = usuario;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		this.Senha = senha;
	}

	public int getIdFuncionario() {
		return IdFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.IdFuncionario = idFuncionario;
	}

}
