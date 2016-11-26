package Entity;
import java.sql.Date;
public class Comanda {
	
	private int idComanda;
	private int idCliente;
	private int idServico;
	private int idJogo;
	private int QuantidadeHoras;
	private String DataComanda;
	private int idFuncionario;
	private int NumeroMesa;
	
	public Comanda(int idCliente, int idServico, int idJogo, int qtdHoras, String dataComanda, int idFuncionario){
		this.idCliente = idCliente;
		this.idServico = idServico;
		this.idJogo = idJogo;
		this.QuantidadeHoras = qtdHoras;
		this.DataComanda = dataComanda;
		this.idFuncionario = idFuncionario;
	}
	
	public Comanda(int idCliente, int idServico, int idJogo, int qtdHoras, int idFuncionario){
		this.idCliente = idCliente;
		this.idServico = idServico;
		this.idJogo = idJogo;
		this.QuantidadeHoras = qtdHoras;
		this.idFuncionario = idFuncionario;
	}
	
	public Comanda(){
		
	}
	
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
	public int getIdJogo() {
		return idJogo;
	}
	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}
	public int getQuantidadeHoras() {
		return QuantidadeHoras;
	}
	public void setQuantidadeHoras(int quantidade) {
		QuantidadeHoras = quantidade;
	}
	public String getDataComanda() {
		return DataComanda;
	}
	public void setDataComanda(String dataComanda) {
		DataComanda = dataComanda;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getNumeroMesa() {
		return NumeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		NumeroMesa = numeroMesa;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

}
