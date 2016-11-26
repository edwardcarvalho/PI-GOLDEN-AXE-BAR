package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import Entity.Cliente;
import Entity.Fornecedor;
import Entity.Funcionario;
import Entity.Jogos;
import Entity.Produto;
import Entity.Usuario;

public class Utilities {

	public static boolean ConvertToBoolean(String item) {
		if (item.equals("0") || item.equalsIgnoreCase("F") || item.equalsIgnoreCase("Feminino")) {
			return false;
		} else if (item.equals("1") || item.equalsIgnoreCase("M") || item.equalsIgnoreCase("Masculino")) {
			return true;
		} else
			return false;
	}

	public static boolean ConvertToBoolean(int item) {
		if (item == 0) {
			return false;
		} else if (item == 1) {
			return true;
		} else
			return false;
	}

	public static Date ConvertToDate(String data) throws java.text.ParseException {

		if (data == null || data.trim().equals(""))
			return null;
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	public static String SerializeClienteToJson(Cliente cliente) {

		int sexo = cliente.getSexo() ? 1 : 0;
		String json = "";
		json += "[{";
		json += "\"nome\" : " + "\"" + cliente.getNome() + "\"";
		json += ", \"cpf\" : " + "\"" + cliente.getCpf() + "\"";
		json += ", \"dataNascimento\" : " + "\"" + cliente.getDataNascimento() + "\"";
		json += ", \"sexo\" : " + "\"" + sexo + "\"";
		json += ", \"telefone\" : " + "\"" + cliente.getTelefone() + "\"";
		json += ", \"email\" : " + "\"" + cliente.getEmail() + "\"";
		json += "}]";

		return json;
	}

	public static String SerializeFuncionarioToJson(Funcionario funcionario, Usuario usuario) {

		int sexo = funcionario.getSexo() ? 1 : 0;
		String json = "";
		json += "[{";
		json += "\"nome\" : " + "\"" + funcionario.getNome() + "\"";
		json += ", \"sexo\" : " + "\"" + sexo + "\"";
		json += ", \"grupo\" : " + "\"" + funcionario.getGrupo() + "\"";
		json += ", \"unidade\" : " + "\"" + funcionario.getUnidade() + "\"";
		json += ", \"usuario\" : " + "\"" + usuario.getUsuario() + "\"";
		json += ", \"senha\" : " + "\"" + usuario.getSenha() + "\"";
		json += "}]";

		return json;
	}

	public static String SerializeFornecedorToJson(Fornecedor fornecedor) {

		String json = "";
		json += "[{";
		json += "\"fornecedor\" : " + "\"" + fornecedor.getNome() + "\"";
		json += ", \"cnpj\" : " + "\"" + fornecedor.getCnpj() + "\"";
		json += ", \"email\" : " + "\"" + fornecedor.getEmail() + "\"";
		json += ", \"telefone\" : " + "\"" + fornecedor.getTelefone() + "\"";
		json += "}]";

		return json;
	}

	public static String SerializeJogosToJson(List<Jogos> listaJogos) {

		String json = "[";

		for (int i = 0; i < listaJogos.size(); i++) {
			if (i == listaJogos.size() - 1) {
				json += "{\"jogo\" : " + "\"" + listaJogos.get(i).getNome() + "\"";
				json += ", \"id\" : " + "\"" + listaJogos.get(i).getIdJogo() + "\"}";
			} else {
				json += "{\"jogo\" : " + "\"" + listaJogos.get(i).getNome() + "\"";
				json += ", \"id\" : " + "\"" + listaJogos.get(i).getIdJogo() + "\"},";
			}
		}
		json += "]";
		return json;
	}
	
	public static String SerializeProdutoToJson(Produto produto) {

		String json = "";
		json += "[{";
		json += "\"nome\" : " + "\"" + produto.getNome() + "\"";
		json += ", \"id\" : " + "\"" + produto.getId() + "\"";
		json += ", \"quantidade\" : " + "\"" + produto.getQuantidade() + "\"";
		json += ", \"valor\" : " + "\"" + produto.getValor() + "\"";
		json += "}]";

		return json;
	}
	
	public static String SerializeIdComandaClienteNome(Cliente cliente, Entity.Comanda comanda){
		String json = "";
		json += "[{";
		json += "\"nome\" : " + "\"" + cliente.getNome() + "\"";
		json += ", \"idComanda\" : " + "\"" + comanda.getIdComanda() + "\"";
		json += ", \"numeroMesa\" : " + "\"" + comanda.getNumeroMesa() + "\"";
		json += "}]";
		
		return json;
	}

}
