package Entity;


public class Usuario {

	private String Usuario;
	private String Senha;
	private int IdFuncionario;
	private boolean Ativo;
	
	public Usuario (String usuario, String senha, int idFuncionario){
		this.Usuario = usuario;
		this.Senha = senha;
		this.IdFuncionario = idFuncionario;
	}
	
	public int getIdFuncionario() {
		return IdFuncionario;
	}
	public void setId_Funcionario(int id_Funcionario) {
		this.IdFuncionario = id_Funcionario;
	}
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
}

