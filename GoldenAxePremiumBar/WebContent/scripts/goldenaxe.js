function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	ev.target.appendChild(document.getElementById(data));
}

function clearForm(idForm) {
	$('#' + idForm).find('input').val('');
	$('#' + idForm).find('select').val('');
}

function clearAlteracaoCliente() {
	clearForm("alterarCadastro,#cadastroCliente");
	$('#cpf').attr('readonly', false);
	$('#alterarCliente').css("display", "none");
	clearForm("alterarCliente,#cadastroCliente");

}
function cadastrarCliente() {
	$.ajax({
		url : 'Cadastro',
		method : 'GET',
		data : {
			'menu' : 'CadastroCliente',
			'nome' : $('#nome').val(),
			'cpf' : $('#cpf').val(),
			'email' : $('#inputIcon').val(),
			'telefone' : $('#telefone').val(),
			'dataNascimento' : $('#dataNascimento').val(),
			'sexo' : $('#sexo').val()
		},
		success : function(data) {
			if (data == "true") {
				alert("Cadastrado com sucesso!");
				clearForm("cadastroCliente");
			} else {
				alert("Erro no cadastro!");
				
			}
		}
	});
}

function alterarCliente() {
	$.ajax({
		url : 'Alteracao',
		method : 'GET',
		data : {
			'menu' : 'AlterarCliente',
			'nome' : $('#nome').val(),
			'cpf' : $('#cpf').val(),
			'email' : $('#inputIcon').val(),
			'telefone' : $('#telefone').val(),
			'dataNascimento' : $('#dataNascimento').val(),
			'sexo' : $('#sexo').val()
		},
		success : function(data) {
			if (data == "true") {
				$('#cpf').attr('readonly', false);
				$('#alterarCliente').css("display", "none");
				clearForm("alterarCliente,#cadastroCliente");
				alert("Alteração realizada com sucesso!");
			} else {
				alert("Erro no processamento da alteração!");
			}
		}
	});
}

function excluirCliente() {
	$.ajax({
		url : 'Exclusao',
		method : 'GET',
		data : {
			'menu' : 'ExcluirCliente',
			'cpf' : $('#cpf').val()
		},
		success : function(data) {
			if (data == "true") {
				$('#cpf').attr('readonly', false);
				$('#alterarCliente').css("display", "none");
				clearForm("alterarCliente,#cadastroCliente");
				alert("Exclusão realizada com sucesso!");
			} else {
				alert("Erro no processamento da exclusão!");
			}
		}
	});
}

function buscarCliente() {
	$.ajax({
		url : 'Cadastro',
		method : 'GET',
		data : {
			'menu' : 'BuscarCliente',
			'cpf' : $('#cpf').val(),
		},
		success : function(data) {
			if (data != null && data != "") {
				data = JSON.parse(data);
				$('#cpf').attr('readonly', true);
				$('#alterarCliente').css("display", "");
				$('#nome').val(data[0].nome);
				$('#inputIcon').val(data[0].email);
				$('#telefone').val(data[0].telefone);
				$('#dataNascimento').val(data[0].dataNascimento);
				$('#sexo option').eq(data[0].sexo).prop('selected', true);
			} else {
				alert("CPF não encontrado!");
			}
		}
	});
}

function cadastrarFuncionario() {
	var pwd = $('#password').val();
	var pwdc = $('#passwordConfirmation').val();

	if (pwd == pwdc) {

		$.ajax({
			url : 'Cadastro',
			method : 'POST',
			data : {
				'menu' : 'CadastroFuncionario',
				'nome' : $('#nome').val(),
				'cpf' : $('#cpf').val(),
				'sexo' : $('#sexo').val(),
				'grupo' : $('#grupo').val(),
				'unidade' : $('#unidade').val(),
				'usuario' : $('#usuario').val(),
				'pswd' : $('#password').val()
			},
			success : function(data) {
				if (data == "true") {
					alert("Cadastrado com sucesso!");
					clearForm("cadastroFuncionario");
				} else {
					alert("Erro no cadastro!");
				}
			}
		});
	}else{
		alert("Senha e confirmação de senha diferentes. Inserir novamente.");
		$('#password').val('');
		$('#passwordConfirmation').val('');
	}
}

$(document).ready(function() {

});