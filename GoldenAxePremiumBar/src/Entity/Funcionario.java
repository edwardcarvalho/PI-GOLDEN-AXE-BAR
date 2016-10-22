package Entity;

import java.sql.Date;

public class Funcionario extends Pessoa {

	private int Tipo;

	public Funcionario(String nome, String cpf, char sexo, Date dataNascimento, int tipo) {

		super(nome, cpf, sexo, dataNascimento);
		this.Tipo = tipo;

	}

	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
		this.Tipo = tipo;
	}

}