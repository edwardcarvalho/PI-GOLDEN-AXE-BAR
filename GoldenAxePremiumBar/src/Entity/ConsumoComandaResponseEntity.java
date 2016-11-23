package Entity;

public class ConsumoComandaResponseEntity {
	
	int idComanda;
	String cpf;
	String nome;
	int tipoServico;
	int jogo;
	String horasReservadas;
	int quantidadeItemConsumo;
	String nomeItemConsumo;
	float precoUnitario;
	int idItemConsumo;
	int idProduto;

	public int getIdItemConsumo() {
		return idItemConsumo;
	}
	public void setIdItemConsumo(int idItemConsumo) {
		this.idItemConsumo = idItemConsumo;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	
	public float getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public int getIdComanda() {
		return idComanda;
	}
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(int tipoServico) {
		this.tipoServico = tipoServico;
	}
	public int getJogo() {
		return jogo;
	}
	public void setJogo(int jogo) {
		this.jogo = jogo;
	}
	public String getHorasReservadas() {
		return horasReservadas;
	}
	public void setHorasReservadas(String horasReservadas) {
		this.horasReservadas = horasReservadas;
	}
	public int getQuantidadeItemConsumo() {
		return quantidadeItemConsumo;
	}
	public void setQuantidadeItemConsumo(int quantidadeItemConsumo) {
		this.quantidadeItemConsumo = quantidadeItemConsumo;
	}
	public String getNomeItemConsumo() {
		return nomeItemConsumo;
	}
	public void setNomeItemConsumo(String nomeItemConsumo) {
		this.nomeItemConsumo = nomeItemConsumo;
	}
	
	

}
