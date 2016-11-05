package Entity;

import java.sql.Date;

public abstract class Pessoa {

	private String Nome;
	private String Cpf;
	private String Email;
	private boolean Sexo;
	private Date DataNascimento;
	private String Telefone;

	public Pessoa(String nome, String cpf, String email, boolean sexo, Date dataNascimento, String telefone) {
		this.Nome = nome;
		this.Cpf = cpf;
		this.Email = email;
		this.Sexo = sexo;
		this.DataNascimento = dataNascimento;
		this.Telefone = telefone;
	}

	public Pessoa(String nome, String cpf, boolean sexo, Date dataNascimento) {
		this.Nome = nome;
		this.Cpf = cpf;
		this.Sexo = sexo;
		this.DataNascimento = dataNascimento;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean getSexo() {
		return Sexo;
	}

	public void setSexo(boolean sexo) {
		Sexo = sexo;
	}

	public Date getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		DataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

}
