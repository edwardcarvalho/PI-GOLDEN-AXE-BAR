package Entity;

public class Jogos {

	private int idJogo;
	private String Nome;
	private int Quantidade;
	private float Valor;
	private int IdFornecedor;
	private boolean Ativo;

	public Jogos(String nome, int quantidade, float valor, int idFornecedor) {

		this.Nome = nome;
		this.Quantidade = quantidade;
		this.Valor = valor;
		this.IdFornecedor = idFornecedor;
	}

	public Jogos(String nome, int quantidade, float valor) {

		this.Nome = nome;
		this.Quantidade = quantidade;
		this.Valor = valor;
	}

	public Jogos() {

	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}

	public float getValor() {
		return Valor;
	}

	public void setValor(float valor) {
		Valor = valor;
	}

	public int getIdFornecedor() {
		return IdFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		IdFornecedor = idFornecedor;
	}

	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}

}
