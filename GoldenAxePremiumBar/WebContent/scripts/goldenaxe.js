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

function getDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();

	if (dd < 10) {
		dd = '0' + dd
	}

	if (mm < 10) {
		mm = '0' + mm
	}

	return today = dd + '/' + mm + '/' + yyyy;

}

// funções de limpeza de tela

function clearAlteracaoCliente() {
	$('#cpf').attr('readonly', false);
	$('#alterarCliente').css("display", "none");
	clearForm("alterarCliente,#cadastroCliente");

}

function clearAlteracaoFuncionario() {
	$('#cpf').attr('readonly', false);
	$('#alterarFuncionario').css("display", "none");
	clearForm("alterarFuncionario,#cadastroFuncionario");

}

function clearAlteracaoFornecedor() {
	$('#cnpj').attr('readonly', false);
	$('#alterarFornecedor').css("display", "none");
	clearForm("alterarFornecedor,#cadastroFornecedor");

}

function clearAlteracaoEstoque() {
	clearForm("alterarEstoque,#cadastroEstoque");
	$('#idProduto').attr('readonly', false);
	$('#alterarEstoque').css("display", "none");
	clearForm("alterarEstoque,#cadastroEstoque");
}
function clearComandaAdmin() {
	clearForm("comandaAdmin");
	$('#numComanda').attr('readonly', false);
	$('#cpf').attr('readonly', false);
	$('#buscarComanda').show();
	$('#tableControleProdutos').hide();

}

// funções de manipulação do cliente

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

// funções de manipulação do funcionario

function buscarFuncionario() {
	$.ajax({
		url : 'Cadastro',
		method : 'POST',
		data : {
			'menu' : 'BuscarFuncionario',
			'cpf' : $('#cpf').val(),
		},
		success : function(data) {
			if (data != null && data != "") {
				data = JSON.parse(data);

				$('#cpf').attr('readonly', true);
				$('#alterarFuncionario').css("display", "");
				$('#nome').val(data[0].nome);
				$('#sexo option').eq(data[0].sexo).prop('selected', true);
				$('#grupo option').val(data[0].grupo).prop('selected', true);
				$('#unidade').val(data[0].unidade).prop('selected', true);
				$('#usuario').val(data[0].usuario);
				psw = data[0].senha;
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
	} else {
		alert("Senha e confirmação de senha diferentes. Inserir novamente.");
		$('#password').val('');
		$('#passwordConfirmation').val('');
	}
}

function alterarFuncionario() {
	var pwdlast = $('#lastPassword').val();
	var pwd = $('#newPassword').val();
	var pwdc = $('#newPasswordConfirmation').val();

	if (psw == pwdlast) {

		if (pwd == pwdc) {

			$.ajax({
				url : 'Alteracao',
				method : 'POST',
				data : {
					'menu' : 'AlterarFuncionario',
					'nome' : $('#nome').val(),
					'cpf' : $('#cpf').val(),
					'sexo' : $('#sexo').val(),
					'grupo' : $('#grupo').val(),
					'unidade' : $('#unidade').val(),
					'usuario' : $('#usuario').val(),
					'pswd' : $('#newPassword').val()
				},
				success : function(data) {
					if (data == "true") {
						$('#cpf').attr('readonly', false);
						$('#alterarFuncionario').css("display", "none");
						clearForm("alterarFuncionario,#cadastroFuncionario");
						alert("Alteração realizada com sucesso!");
					} else {
						alert("Erro no processamento da alteração!");
					}
				}
			});
		} else {
			alert("Senha e confirmação de senha diferentes. Inserir novamente.");
			$('#newPassword').val('');
			$('#newPasswordConfirmation').val('');
		}
	} else {

		alert("Senha anterior incorreta. Inserir novamente.");
		$('#lastPassword').val('');
		$('#newPassword').val('');
		$('#newPasswordConfirmation').val('');
	}
}

function excluirFuncionario() {
	$.ajax({
		url : 'Exclusao',
		method : 'GET',
		data : {
			'menu' : 'ExcluirFuncionario',
			'cpf' : $('#cpf').val()
		},
		success : function(data) {
			if (data == "true") {
				$('#cpf').attr('readonly', false);
				$('#alterarFuncionario').css("display", "none");
				clearForm("alterarFuncionario,#cadastroFuncionario");
				alert("Exclusão realizada com sucesso!");
			} else {
				alert("Erro no processamento da exclusão!");
			}
		}
	});
}

// funções de manipulação de fornecedors

function buscarFornecedor() {
	$.ajax({
		url : 'Cadastro',
		method : 'GET',
		data : {
			'menu' : 'BuscarFornecedor',
			'cnpj' : $('#cnpj').val(),
		},
		success : function(data) {
			if (data != null && data != "") {
				data = JSON.parse(data);
				$('#cnpj').attr('readonly', true);
				$('#alterarFornecedor').css("display", "");
				$('#fornecedor').val(data[0].fornecedor);
				$('#inputIcon').val(data[0].email);
				$('#telefone').val(data[0].telefone);
			} else {
				alert("CNPJ não encontrado!");
			}
		}
	});
}

function cadastrarFornecedor() {
	$.ajax({
		url : 'Cadastro',
		method : 'GET',
		data : {
			'menu' : 'CadastroFornecedor',
			'fornecedor' : $('#fornecedor').val(),
			'cnpj' : $('#cnpj').val(),
			'email' : $('#inputIcon').val(),
			'telefone' : $('#telefone').val()
		},
		success : function(data) {
			if (data == "true") {
				alert("Cadastrado com sucesso!");
				clearForm("fornecedorCadastro");
			} else {
				alert("Erro no cadastro!");
			}
		}
	});
}

function alterarFornecedor() {
	$.ajax({
		url : 'Alteracao',
		method : 'GET',
		data : {
			'menu' : 'AlterarFornecedor',
			'fornecedor' : $('#fornecedor').val(),
			'cnpj' : $('#cnpj').val(),
			'email' : $('#inputIcon').val(),
			'telefone' : $('#telefone').val()
		},
		success : function(data) {
			if (data == "true") {
				$('#cnpj').attr('readonly', false);
				$('#alterarFornecedor').css("display", "none");
				clearForm("alterarFornecedor,#fornecedorAlterar");
				alert("Alteração realizada com sucesso!");
			} else {
				alert("Erro no processamento da alteração!");
			}
		}
	});
}

function excluirFornecedor() {
	$.ajax({
		url : 'Exclusao',
		method : 'GET',
		data : {
			'menu' : 'ExcluirFornecedor',
			'cnpj' : $('#cnpj').val()
		},
		success : function(data) {
			if (data == "true") {
				$('#cnpj').attr('readonly', false);
				$('#alterarFornecedor').css("display", "none");
				clearForm("alterarFornecedor,#cadastroFornecedor");
				alert("Exclusão realizada com sucesso!");
			} else {
				alert("Erro no processamento da exclusão!");
			}
		}
	});
}

// funções de manipulação da comanda

function abrirComanda() {
	$.ajax({
		url : 'Comanda',
		data : {
			'menu' : 'AbrirComanda'
		},
		method : 'GET',
		success : function(data) {
			if (data != "" && data != undefined) {
				$('#numComanda').val(data);
				$('#numComanda').attr('readonly', true);
				$('#numComanda').attr('readonly', true);
				$('#numComanda').attr('readonly', true);
				$('#buscarComanda').hide();
				$('#tableControleProdutos').css("display", "");
			}
		}
	});
}

function salvarComanda() {
	
	var produtos = JSON.stringify(salvarProdutosDaComanda());
	
	$.ajax({
		url : 'Comanda',
		dataType : 'json',
		data : {
			'menu' : 'CadastrarComanda',
			'cpf' : $('#cpf').val(),
			'servico' : $('#tpServico').val(),
			'jogo' : $('#jogos').val(),
			'horas' : $('#qtdHoras').val(),
			'data' : getDate(),
			'numComanda' : $('#numComanda').val(),
			'produtos' : produtos
		},
		method : 'GET',
		success : function(data) {
			if (data == "true" || data == true) {
				alert("Cadastro efetuado com sucesso!");
				clearForm('comandaAdmin');
				$('#numComanda').attr('readonly', false);
				$('#cpf').attr('readonly', false);
				$('#buscarComanda').show();
			} else {
				alert("Erro no cadastro!");
			}
		}
	});
}

var sequencia = 1;
function adicionarItemComanda() {
	var itemId = "produto" + sequencia;
	var line = "<tr id="
			+ itemId
			+ ">"
			+ "<th scope='row'>"
			+ sequencia
			+ "</th>"
			+ "<td><input class='quantidade' type='text'></td>"
			+ "<td><input class='produto' type='text'list='listItems"
			+ sequencia
			+ "' onfocusout='verificaProdutoCadastrado("
			+ itemId
			+ ")'>"
			+ "<datalist id='listItems"
			+ sequencia
			+ "'></datalist></td>"
			+ "<td><input class='preco' type='text'disabled></td>"
			+ "<td><input class='total' type='text' disabled></td>"
			+ "<td height='30px' width='50px'><a id='salvar' href='#' class='btn btn-success' role='button' onclick=\"btnSalvarProduto("
			+ itemId
			+ ")\">Salvar</a></td>"
			+ "<td height='30px' width='50px'><a id='editar' href='#' class='btn btn-warning' role='button' onclick='btnEditarProduto("
			+ itemId
			+ ")'>Editar</a></td>"
			+ "<td height='30px' width='50px'><a id='excluir'href='#' class='btn btn-danger' role='button' onclick='btnRemoverProduto("
			+ itemId + ")'>Excluir</a></td>" + "</tr>";

	$('#tableControleProdutos').find('tbody:nth-child(2)').append(line);
	sequencia++;
}

function btnSalvarProduto(element) {
	var check = false;
	var linha = $('#' + element.id).find('input').each(function(index, item) {
		var item = $(item).val();
		if (item == "" || item == undefined) {
			alert("Preencha todos os campos!");
			check = false;
			return false;
		} else {
			check = true;
		}
	});
	if (check) {
		$('#' + element.id).find('input').attr('readonly', true);
		var qtd = parseInt($('#' + element.id).find('.quantidade').val());
		var valorProduto = parseInt($('#' + element.id).find('.preco').val());
		$('#' + element.id).find('.total').val(valorProduto * qtd + ",00");
	}
}

function btnEditarProduto(element) {
	$('#' + element.id).find('input').attr('readonly', false);
}

function btnRemoverProduto(element) {
	$('#' + element.id).remove();
}

function verificaProdutoCadastrado(idTextBox) {
	var produto = $('#' + idTextBox.id).find('.produto').val();
	var qtd = $('#' + idTextBox.id).find('.quantidade').val();
	if (produto != "" && produto.length > 2) {
		if (qtd > 0) {
			$.ajax({
				url : 'Comanda',
				data : {
					'menu' : 'VerificaProdutoCadastrado',
					'produto' : produto
				},
				method : 'GET',
				success : function(data) {
					data = JSON.parse(data);
					if (data[0].nome != "null") {
						$('#' + idTextBox.id).val(data[0].id);
						var valorProduto = data[0].valor;
						$('#' + idTextBox.id).find('.preco').val(valorProduto.replace(".0", ",00"));
						$('#' + idTextBox.id).find('.total').val(valorProduto * qtd + ",00");
					} else {
						alert("Produto não cadastrado!");
						$('#produto1').val("");
						$('#' + idTextBox.id).find('.produto').val("");
						$('#' + idTextBox.id).find('.preco').val("");
						$('#' + idTextBox.id).find('.total').val("");
						return false;
					}
				}
			});
		} else {
			alert("Preencha o campo de quantidade!");
			return false;
		}
	} else {
		$('#' + idTextBox.id).find('.preco').val("");
		$('#' + idTextBox.id).find('.total').val("");
	}
}

function salvarProdutosDaComanda(){
	var listaDeProdutos = [];
	var produtosInseridos = $('#tableControleProdutos').find('input.produto');
	if(produtosInseridos.length > 0){
		$(produtosInseridos).each(function(index, item){
			var obj = {idProduto : $(item).parent().parent().val(), Quantidade:$(item).parent().parent().find('.quantidade').val()};
			listaDeProdutos[index] = obj;
		});
		return listaDeProdutos;
	}
	return undefined;
}

$(document).ready(function() {
			var psw;
			// alimenta o combobox com todos os jogos ao carregar a pagina.
			$.ajax({
				url : 'Comanda',
				method : 'GET',
				data : {
					'menu' : 'CarregarJogos'
				},
				success : function(data) {
					if (data != "" && data != undefined) {
						data = JSON.parse(data);
						for (var i = 0; i < data.length; i++) {
							$('#jogos').append(
									'<option value=' + data[i].id + '>'
											+ data[i].jogo + '</option>');
						}
					}
				}
			});

			// $(function () {
			// var availableTags = [
			// { value: "ActionScript", data: "1" },
			// { value: "AppleScript", data: "2" },
			// { value: "Asp", data: "3" }
			// ];
			// jQuery('#textBoxBrowsers')
			// .on('keypress',
			// function (e) {
			// if (e.which != 13)
			// $('#browsers').find('option').remove();
			// jQuery(availableTags)
			// .each(function (index, item) {
			// $('#browsers').append("<option value=" + item.data + ">" +
			// item.value + "</option>");
			// });
			// });
			// });
		});
