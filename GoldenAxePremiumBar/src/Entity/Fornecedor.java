package Entity;

public class Fornecedor {

	private String Cnpj;
	private String Nome;
	private String Telefone;
	private String Email;

	public Fornecedor(String cnpj, String nome, String telefone, String email) {
		this.Cnpj = cnpj;
		this.Nome = nome;
		this.Telefone = telefone;
		this.Email = email;
	}

	public String getCnpj() {
		return Cnpj;
	}

	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	

}