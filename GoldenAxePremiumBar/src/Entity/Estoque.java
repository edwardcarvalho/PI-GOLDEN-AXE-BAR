package Entity;

public class Estoque {

	private int IdFornecedor;
	private int IdProduto;
	private int IdJogo;
	private int Quantidade;
	private float Valor;
	private int IdUnidade;

	public Estoque(int idFornecedor, int idProduto, int idJogo, int quantidade, float valor, int idUnidade) {

		this.IdFornecedor = idFornecedor;
		this.IdProduto = idProduto;
		this.IdJogo = idJogo;
		this.Quantidade = quantidade;
		this.Valor = valor;
		this.IdUnidade = idUnidade;

	}

	public Estoque() {
		// TODO Auto-generated constructor stub
	}

	public int getIdFornecedor() {
		return IdFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		IdFornecedor = idFornecedor;
	}

	public int getIdProduto() {
		return IdProduto;
	}

	public void setIdProduto(int idProduto) {
		IdProduto = idProduto;
	}

	public int getIdJogo() {
		return IdJogo;
	}

	public void setIdJogo(int idJogo) {
		IdJogo = idJogo;
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

	public int getIdUnidade() {
		return IdUnidade;
	}

	public void setIdUnidade(int idUnidade) {
		IdUnidade = idUnidade;
	}
}
