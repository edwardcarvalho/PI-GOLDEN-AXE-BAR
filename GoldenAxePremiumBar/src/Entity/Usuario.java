package Entity;

public class Usuario {

	private String User;
	private String Senha;
	private byte senhaCryp;
	private int IdFuncionario;
	private boolean Ativo;
	private int idGrupoFuncionario;

	
	public Usuario(String usuario, String senha, int idFuncionario) {
		this.User = usuario;
		this.Senha = senha;
		this.IdFuncionario = idFuncionario;
	}
	
	public Usuario(String usuario, byte senhaCryp, int idFuncionario) {
		this.User = usuario;
		this.senhaCryp = senhaCryp;
		this.IdFuncionario = idFuncionario;
	}
	
	public Usuario(String usuario, String senha, int idFuncionario, int idGrupoFuncionario) {
		this.User = usuario;
		this.Senha = senha;
		this.IdFuncionario = idFuncionario;
		this.idGrupoFuncionario = idGrupoFuncionario;
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

	public int getIdGrupoFuncionario() {
		return idGrupoFuncionario;
	}

	public void setIdGrupoFuncionario(int idGrupoFuncionario) {
		this.idGrupoFuncionario = idGrupoFuncionario;
	}

	public byte getSenhaCryp() {
		return senhaCryp;
	}

	public void setSenhaCryp(byte senhaCryp) {
		this.senhaCryp = senhaCryp;
	}
}
