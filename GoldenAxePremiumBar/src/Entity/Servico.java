package Entity;

public class Servico {

	private String Descricao;
	private float ValorHora;

	public Servico(String descricao, float valorHora) {

		this.Descricao = descricao;
		this.ValorHora = valorHora;
		// TODO Auto-generated constructor stub
	}

	public Servico() {

	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public float getValorHora() {
		return ValorHora;
	}

	public void setValorHora(float valorHora) {
		ValorHora = valorHora;
	}

}
