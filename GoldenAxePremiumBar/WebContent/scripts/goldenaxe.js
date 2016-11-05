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

function clearForm(idForm){
	$('#'+idForm).find('input:text').val('');
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

$(document).ready(function() {

});