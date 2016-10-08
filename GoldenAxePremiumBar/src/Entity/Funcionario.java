package Entity;

import java.sql.Date;

public class Funcionario {
	
	private String Nome;
	private String Cpf;
	private char Sexo;
	private String IdUnidade;
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
	public char getSexo() {
		return Sexo;
	}
	public void setSexo(char sexo) {
		Sexo = sexo;
	}
	public String getIdUnidade() {
		return IdUnidade;
	}
	public void setIdUnidade(String idUnidade) {
		IdUnidade = idUnidade;
	}
}