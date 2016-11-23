package Entity;

public class Consumo {
	
	int id;
	int idProduto;
	int Quantidade;
	int idComanda;
	
	public Consumo (int idProduto, int idComanda, int quantidadeProduto){
		this.idProduto = idProduto;
		this.idComanda = idComanda;
		this.Quantidade = quantidadeProduto;
	}
	
	public Consumo (int id, int idProduto, int idComanda, int quantidadeProduto){
		this.id = id;
		this.idProduto = idProduto;
		this.idComanda = idComanda;
		this.Quantidade = quantidadeProduto;
	}
	
	public Consumo() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidadeProduto(int quantidadeProduto) {
		Quantidade = quantidadeProduto;
	}
	public int getIdComanda() {
		return idComanda;
	}
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

}
