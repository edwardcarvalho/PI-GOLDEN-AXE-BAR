package Entity;

public class Usuario {

	private String User;
	private String Senha;
	private int IdFuncionario;
	private boolean Ativo;

	public Usuario(String usuario, String senha, int idFuncionario) {
		this.User = usuario;
		this.Senha = senha;
		this.IdFuncionario = idFuncionario;
	}

	public Usuario() {

	}

	public int getIdFuncionario() {
		return IdFuncionario;
	}

	public void setId_Funcionario(int id_Funcionario) {
		this.IdFuncionario = id_Funcionario;
	}

	public String getUsuario() {
		return User;
	}

	public void setUsuario(String usuario) {
		this.User = usuario;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		this.Senha = senha;
	}
}
