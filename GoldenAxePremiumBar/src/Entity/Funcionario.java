package Entity;

import java.sql.Date;

public class Funcionario extends Pessoa {

	private int Tipo;
	private String Unidade;

	public Funcionario(String nome, String cpf, boolean sexo, Date dataNascimento, int tipo, String unidade) {

		super(nome, cpf, sexo, dataNascimento);
		this.Tipo = tipo;
		this.Unidade = unidade;

	}
	
	public String getUnidade(){
		return Unidade;
	}
	
	public void setUnidade(String unidade){
		this.Unidade = unidade;
	}

	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
		this.Tipo = tipo;
	}

}