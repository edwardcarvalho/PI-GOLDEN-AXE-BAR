package Entity;


public class Estoque {
	
	private int IdFornecedor;
	private int IdProduto;
	private int IdJogo;
	private int Quantidade;
	private float Valor;
	private int IdUnidade;
	
	public Estoque(String idFornecedor2, String idProduto2, String idJogo2, String quantidade2, String valor2,
			String idUnidade2) {
		// TODO Auto-generated constructor stub
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
