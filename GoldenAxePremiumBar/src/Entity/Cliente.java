package Entity;

import java.sql.Date;

public class Cliente extends Pessoa {

	public Cliente(String nome, String cpf, String email, char sexo, Date dataNascimento, String telefone) {

		super(nome, cpf, email, sexo, dataNascimento, telefone);
	}

}
