package Entity;

import java.sql.Date;

public class Cliente extends Pessoa {

	public Cliente(String nome, String cpf, String email, boolean sexo, Date dataNascimento, String telefone) {

		super(nome, cpf, email, sexo, dataNascimento, telefone);
	}

}
