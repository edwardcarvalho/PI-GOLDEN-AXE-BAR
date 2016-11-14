package Entity;

public class Funcionario extends Pessoa {

	private int Grupo;
	private int Unidade;
	private int id;

	public Funcionario(String nome, String cpf, boolean sexo, int grupo, int unidade) {

		super(nome, cpf, sexo);
		this.Grupo = grupo;
		this.Unidade = unidade;

	}
	public Funcionario(int id, String nome, String cpf, boolean sexo, int grupo, int unidade) {

		super(nome, cpf, sexo);
		this.Grupo = grupo;
		this.Unidade = unidade;
		this.id = id;

	}
	
	public int getUnidade(){
		return Unidade;
	}
	
	public void setUnidade(int unidade){
		this.Unidade = unidade;
	}

	public int getGrupo() {
		return Grupo;
	}

	public void setTipo(int tipo) {
		this.Grupo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}