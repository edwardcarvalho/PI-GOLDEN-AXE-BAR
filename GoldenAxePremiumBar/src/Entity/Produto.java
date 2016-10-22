package Entity;

public class Produto {

	private String Nome;
	private int Quantidade;
	private int Valor;
	private int IdFornecedor;
	
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
