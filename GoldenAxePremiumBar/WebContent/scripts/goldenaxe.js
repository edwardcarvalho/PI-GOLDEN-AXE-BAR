function getDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; // January is 0!
    var yyyy = today.getFullYear();

    if (dd < 10) {
        dd = '0' + dd;
    }

    if (mm < 10) {
        mm = '0' + mm;
    }

    return today = dd + '/' + mm + '/' + yyyy;

}

// funções de limpeza de tela
function clearForm(idForm) {
    $('#' + idForm).find('input').val('');
    $('#' + idForm).find('select').val('');
}

function clearAlteracaoCliente() {
    $('#cpf').attr('readonly', false);
    $('#alterarCliente').css("display", "");
    clearForm("alterarCliente,#cadastroCliente");

}

function clearAlteracaoFuncionario() {
    $('#cpf').attr('readonly', false);
    $('#alterarFuncionario').css("display", "none");
    clearForm("alterarFuncionario,#cadastroFuncionario");

}

function clearAlteracaoFornecedor() {
    $('#cnpj').val('').attr('readonly', false);
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
    if ($('#alterarForm').is(':visible')) {
        $('#alterarForm').hide();
        $('#salvarForm').show();
    }
    clearForm("comandaAdmin");
    $('#numComanda').attr('readonly', false);
    $('#cpf').attr('readonly', false);
    $('#tableControleProdutos').hide();
    $('#abrirComanda, #comandaAdmin, #btnInferiorComanda').css('display', 'none');
    $('table tbody:first').find('tr').remove();
    sequencia = 1;
}

// funções de manipulação do cliente

function cadastrarCliente() {
    $.ajax({
        url: 'Cadastro',
        method: 'GET',
        data: {
            'menu': 'CadastroCliente',
            'nome': $('#nome').val(),
            'cpf': $('#cpf').val(),
            'email': $('#inputIcon').val(),
            'telefone': $('#telefone').val(),
            'dataNascimento': $('#dataNascimento').val(),
            'sexo': $('#sexo').val()
        },
        success: function (data) {
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
        url: 'Alteracao',
        method: 'GET',
        data: {
            'menu': 'AlterarCliente',
            'nome': $('#nome').val(),
            'cpf': $('#cpf').val(),
            'email': $('#inputIcon').val(),
            'telefone': $('#telefone').val(),
            'dataNascimento': $('#dataNascimento').val(),
            'sexo': $('#sexo').val()
        },
        success: function (data) {
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
        url: 'Exclusao',
        method: 'GET',
        data: {
            'menu': 'ExcluirCliente',
            'cpf': $('#cpf').val()
        },
        success: function (data) {
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
        url: 'Cadastro',
        method: 'GET',
        data: {
            'menu': 'BuscarCliente',
            'cpf': $('#cpf').val(),
        },
        success: function (data) {
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
        url: 'Cadastro',
        method: 'POST',
        data: {
            'menu': 'BuscarFuncionario',
            'cpf': $('#cpf').val(),
        },
        success: function (data) {
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
            url: 'Cadastro',
            method: 'POST',
            data: {
                'menu': 'CadastroFuncionario',
                'nome': $('#nome').val(),
                'cpf': $('#cpf').val(),
                'sexo': $('#sexo').val(),
                'grupo': $('#grupo').val(),
                'unidade': $('#unidade').val(),
                'usuario': $('#usuario').val(),
                'pswd': $('#password').val()
            },
            success: function (data) {
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
                url: 'Alteracao',
                method: 'POST',
                data: {
                    'menu': 'AlterarFuncionario',
                    'nome': $('#nome').val(),
                    'cpf': $('#cpf').val(),
                    'sexo': $('#sexo').val(),
                    'grupo': $('#grupo').val(),
                    'unidade': $('#unidade').val(),
                    'usuario': $('#usuario').val(),
                    'pswd': $('#newPassword').val()
                },
                success: function (data) {
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
        url: 'Exclusao',
        method: 'GET',
        data: {
            'menu': 'ExcluirFuncionario',
            'cpf': $('#cpf').val()
        },
        success: function (data) {
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
        url: 'Cadastro',
        method: 'GET',
        data: {
            'menu': 'BuscarFornecedor',
            'cnpj': $('#cnpj').val(),
        },
        success: function (data) {
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
        url: 'Cadastro',
        method: 'GET',
        data: {
            'menu': 'CadastroFornecedor',
            'fornecedor': $('#fornecedor').val(),
            'cnpj': $('#cnpj').val(),
            'email': $('#inputIcon').val(),
            'telefone': $('#telefone').val()
        },
        success: function (data) {
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
        url: 'Alteracao',
        method: 'GET',
        data: {
            'menu': 'AlterarFornecedor',
            'fornecedor': $('#fornecedor').val(),
            'cnpj': $('#cnpj').val(),
            'email': $('#inputIcon').val(),
            'telefone': $('#telefone').val()
        },
        success: function (data) {
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
        url: 'Exclusao',
        method: 'GET',
        data: {
            'menu': 'ExcluirFornecedor',
            'cnpj': $('#cnpj').val()
        },
        success: function (data) {
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


function salvarComanda() {

    var produtos = JSON.stringify(salvarProdutosDaComanda());

    $.ajax({
        url: 'Comanda',
        dataType: 'json',
        data: {
            'menu': 'CadastrarComanda',
            'cpf': $('#cpf').val(),
            'servico': $('#tpServico').val(),
            'jogo': $('#jogos').val(),
            'horas': $('#qtdHoras').val(),
            'data': getDate(),
            'numComanda': $('#numComanda').val(),
            'produtos': produtos
        },
        method: 'GET',
        success: function (data) {
            if (data == "true" || data == true) {
                alert("Operação realizada com sucesso!");
                clearForm('comandaAdmin');
                clearComandaAdmin();
                $('#numComanda').attr('readonly', false);
                $('#cpf').attr('readonly', false);
                $('#buscarComanda').show();
            } else {
                alert("Erro no cadastro!");
            }
        }
    });
}

function abrirComanda() {
	$.ajax({
		url: 'Comanda',
		data: {
			'menu': 'AbrirComanda'
		},
		method: 'GET',
		success: function (data) {
			if (data != "" && data != undefined) {
				$('#numComanda').val(data);
				$('#numComanda').attr('readonly', true);
				$('#buscarComanda').hide();
				$('#cpf').attr('readonly', false);
//				$('#tableControleProdutos').css("display", "");
				$('#abrirComanda, #comandaAdmin, #btnInferiorComanda').css('display', "");
			}
		}
	});
}

function alterarComanda() {
    $('#salvarForm').css('display', 'none');
    $('#abrirComanda, #comandaAdmin, #btnInferiorComanda, #alterarForm').css('display', '');
    $('#buscarComanda').show();
    $('#numComanda').val('').attr('readonly', false);
    $('#buscarCliente').hide();
    $('#cpf').attr('readonly', true);
}

function buscarComandaCliente() {

    var idComanda = $('#numComanda').val();
    var table = $('#tableControleProdutos');

    if (idComanda != 0 && idComanda != "0" && idComanda != "" && !table.is(':visible')) {
        $.ajax({
            url: 'Comanda',
            method: 'GET',
            data: { 'menu': 'BuscarComandaCliente', 'idComanda': idComanda },
            success: function (data) {
                if (data != "" && data != undefined) {
                    $('#numComanda').attr('readonly', true);
                    data = JSON.parse(data);

                    $(data).each(function (index, item) {
                        if (index == 0) {
                            $('#cpf').val(item.cpf);
                            $('#nome').val(item.nome);
                            $('#tpServico option').eq(item.tipoServico).prop('selected', true);;
                            $('#jogos option').eq(item.jogo).prop('selected', true);
                            $('#qtdHoras').val(item.horasReservadas);
                            table.css('display', "");
                            adicionarItemComanda();
                            var linha = $("#produto1");
                            linha.find('.idItemConsumo').val(item.idItemConsumo);
                            linha.find('.quantidade').val(item.quantidadeItemConsumo).attr('readonly', true);
                            linha.find('.idProduto').val(item.idProduto);
                            linha.find('.produto').val(item.nomeItemConsumo).attr('readonly', true);
                            linha.find('.preco').val(item.precoUnitario.toFixed(2).replace(".", ","));
                            linha.find('.total').val((item.quantidadeItemConsumo * item.precoUnitario).toFixed(2).replace(".", ","));
                        } else {
                            adicionarItemComanda();
                            var linha = $("#produto" + (index+1));
                            linha.find('.idItemConsumo').val(item.idItemConsumo);
                            linha.find('.quantidade').val(item.quantidadeItemConsumo).attr('readonly', true);
                            linha.find('.idProduto').val(item.idProduto);
                            linha.find('.produto').val(item.nomeItemConsumo).attr('readonly', true);
                            linha.find('.preco').val(item.precoUnitario.toFixed(2).replace(".", ","));
                            linha.find('.total').val((item.quantidadeItemConsumo * item.precoUnitario).toFixed(2).replace(".", ","));

                        }
                    });
                }
            }
        });
    }
}

$(document).ready(function () {
    var psw;
    // alimenta o combobox com todos os jogos ao carregar a pagina.
    $.ajax({
        url: 'Comanda',
        method: 'GET',
        data: {
            'menu': 'CarregarJogos'
        },
        success: function (data) {
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
    
    //acrescenta mais um evento no botão buscar, quando
    $('#buscarCliente').on('click', function(){
    	var cpf = $('#cpf').val();
    	if(cpf != ""){
    		$('#tableControleProdutos').css("display", "");
			$('#abrirComanda, #comandaAdmin, #btnInferiorComanda').css('display', "");
    	}
    });
});

//**********/23/11/2016**************

function btnSalvarProduto(element) {
	var check = false;
	var elementX = $("#" + element.id);
	var idItemConsumo = elementX.find('.idItemConsumo').val();
	if (idItemConsumo != "" && idItemConsumo != 0) {
		alterarItemComanda(element);
		check = true;
	} else {
		elementX.find('input:visible').each(function (index, item) {
			var itemValue = $(item).val();
			if (itemValue == "" || itemValue == undefined) {
				alert("Preencha todos os campos!");
				check = false;
				return false;
			} else {
				check = true;
			}
		});
	}
	
	if (check) {
		elementX.find('input').attr('readonly', true);
		var qtd = parseInt(elementX.find('.quantidade').val());
		var valorProduto = parseInt(elementX.find('.preco').val());
		elementX.find('.total').val((valorProduto * qtd).toFixed(2).replace(".", ","));
	}
}

function btnEditarProduto(element) {
    var elementX = $("#" + element.id);
    elementX.find('input').attr('readonly', false);
}


function btnRemoverProduto(element) {
    var idItemConsumo = $('#' + element.id).find('.idItemConsumo').val();
    if (idItemConsumo == 0 || idItemConsumo == "") {
        $('#' + element.id).remove();
    } else {
        $('#' + element.id).remove();
        excluirItemComanda(idItemConsumo);
    }
}

function alterarItemComanda(element) {
    element = $('#' + element.id);
    var idItemConsumo = element.find('.idItemConsumo').val();
    var quantidade = element.find('.quantidade').val();
    var idProduto = element.find('.idProduto').val();
    var idComanda = $('#numComanda').val();
    $.ajax({
        url: 'Comanda',
        method: 'GET',
        data: { 'menu': 'AlterarItemConsumo', 'idItemConsumo': idItemConsumo, 'idComanda': idComanda, 'quantidade': quantidade, 'produto': idProduto },
        success: function (data) {
            if (data == true || data == "true") {
                alert("Alterado com sucesso!");
            } else {
                alert("Erro na alteração!");
            }
        }
    });
}

function excluirItemComanda(idItemConsumo) {
    $.ajax({
        url: 'Comanda',
        method: 'GET',
        data: { 'menu': 'ExcluirItemConsumo', 'idItemConsumo': idItemConsumo, 'idComanda': $('#numComanda').val() },
        success: function (data) {
            if (data == true || data == "true") {
                alert("Excluido com sucesso!");
            } else {
                alert("Erro na exclusão!");
            }
        }
    });
}

var sequencia = 1;
function adicionarItemComanda() {
    var itemId = "produto" + sequencia;
    var trInit = "<tr id="+itemId+">";
    var th = "<th scope='row'>"+sequencia+"</th>"; //indice da tabela
    var td1 = "<td style='display:none'><input class='idItemConsumo' type='text'></td>";//coluna idConsumo
    var td2 = "<td><input class='quantidade' type='text'></td>"; //coluna quantidade
    var td3 = "<td style='display:none'><input class='idProduto' type='text'></td>"; //coluna idProduto
    var td4 = "<td><input class='produto' type='text' onfocusout='verificaProdutoCadastrado("+itemId+")'></td>"; //coluna nome produto
    var td5 = "<td><input class='preco' type='text'disabled></td>"; //coluna preço
    var td6 = "<td><input class='total' type='text' disabled></td>"; //coluna total
    var td7Btn = "<td height='30px' width='50px'><a href='#' class='btn btn-success salvar' role='button' onclick='btnSalvarProduto("+itemId+")'>Salvar</a></td>"; //btn salvar
    var td8Btn = "<td height='30px' width='50px'><a href='#' class='btn btn-warning editar' role='button' onclick='btnEditarProduto("+itemId+")'>Editar</a></td>"; //btn editar
    var td9Btn = "<td height='30px' width='50px'><a href='#' class='btn btn-danger excluir' role='button' onclick='btnRemoverProduto("+itemId+")'>Excluir</a></td>"; //btn remover
    var trEnd = "</tr>";
    var line = trInit + th + td1 + td2 + td3 + td4 + td5 + td6 + td7Btn + td8Btn + td9Btn + trEnd;
    $('#tableControleProdutos').find('tbody:nth-child(2)').append(line);
    sequencia++;
}

function verificaProdutoCadastrado(idTextBox) {
    var element = $('#' + idTextBox.id);
    var produto = element.find('.produto').val();
    var qtd = element.find('.quantidade').val();
    if (produto != "" && produto.length > 2) {
        if (qtd > 0) {
            $.ajax({
                url: 'Comanda',
                data: {
                    'menu': 'VerificaProdutoCadastrado',
                    'produto': produto
                },
                method: 'GET',
                success: function (data) {
                    data = JSON.parse(data);
                    if (data[0].nome != "null") {
                        element.find('.idProduto').val(data[0].id);
                        var valorProduto = parseInt(data[0].valor);
                        element.find('.preco').val(valorProduto.toFixed(2).replace(".", ","));
                        element.find('.total').val((valorProduto * qtd).toFixed(2).replace(".", ","));
                    } else {
                        alert("Produto não cadastrado!");
                        element.find('.idProduto').val("");
                        element.find('.produto').val("");
                        element.find('.preco').val("");
                        element.find('.total').val("");
                        return false;
                    }
                }
            });
        } else {
            alert("Preencha o campo de quantidade!");
            return false;
        }
    } else {
        element.find('.preco').val("");
        element.find('.total').val("");
    }
}

function salvarProdutosDaComanda() {
	var i = 0;
    var listaDeProdutos = [];
    var produtosInseridos = $('#tableControleProdutos tr')
    if (produtosInseridos.length > 0) {
        $(produtosInseridos).each(function (index, item) {
            var productLine = $(item);
            var hasIdComanda = productLine.find('td .idItemConsumo').val();
            if( hasIdComanda == ""){
            	var obj = { idProduto: productLine.find('td .idProduto').val(), Quantidade: productLine.find('td .quantidade').val() };
            	listaDeProdutos[i] = obj;
            	i++;
            }
        });
        return listaDeProdutos;
    }
    return undefined;
}