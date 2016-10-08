package Entity;

public class Produto {

	private int Nome;
	private int Quantidade;
	private int Valor;
	private int IdFornecedor;
	
	public int getNome() {
		return Nome;
	}
	public void setNome(int nome) {
		Nome = nome;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	public int getValor() {
		return Valor;
	}
	public void setValor(int valor) {
		Valor = valor;
	}
	public int getIdFornecedor() {
		return IdFornecedor;
	}
	public void setIdFornecedor(int idFornecedor) {
		IdFornecedor = idFornecedor;
	}
	
}
