package Entity;

import java.sql.Date;

public class Funcionario extends Pessoa {

	private int tipo;

	public Funcionario(String nome, String cpf, char sexo, Date dataNascimento, int tipo) {

		super(nome, cpf, sexo, dataNascimento);
		this.tipo = tipo;

	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}