package Servlet;

public class Mesa {
	
	int id;
	int numero;
	int tipo;
	String descricao;
	int status;
	int idComanda;
	
	

	public Mesa() {
		// TODO Auto-generated constructor stub
	}
	public Mesa (int id, int numero, int tipo, String descricao, int status){
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.descricao = descricao;
		this.status = status;
		this.idComanda = idComanda;
	}
	
	public Mesa (int id, int numero, int tipo, String descricao, int status, int idComanda){
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.descricao = descricao;
		this.status = status;
		this.idComanda = idComanda;
	}
	public int getIdComanda() {
		return idComanda;
	}
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
