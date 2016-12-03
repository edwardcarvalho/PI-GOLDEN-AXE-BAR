package Entity;

public class Produto {

	private int id;
	private String Nome;
	private int Quantidade;
	private float Valor;
	private int IdFornecedor;
	private boolean Ativo;

	public Produto(String nome, int quantidade, float valor, int idFornecedor) {

		this.Nome = nome;
		this.Quantidade = quantidade;
		this.Valor = valor;
		this.IdFornecedor = idFornecedor;
	}
	
	public Produto(String nome, float valor, int idFornecedor) {

		this.Nome = nome;
		this.Valor = valor;
		this.IdFornecedor = idFornecedor;
	}

	public Produto(String nome, int quantidade, float valor) {

		this.Nome = nome;
		this.Quantidade = quantidade;
		this.Valor = valor;
	}
	public Produto() {

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
