package Entity;

public class Comanda {
	
	private int idCliente;
	private int idServico;
	private int idProduto;
	private int idJogo;
	private int Quantidade;
	private int DataComanda;
	private int idFuncionario;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdServico() {
		return idServico;
	}
	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getIdJogo() {
		return idJogo;
	}
	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	public int getDataComanda() {
		return DataComanda;
	}
	public void setDataComanda(int dataComanda) {
		DataComanda = dataComanda;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

}
