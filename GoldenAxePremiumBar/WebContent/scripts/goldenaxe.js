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

function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function validateInputsAndSelectOption() {
  var fields = $('input:visible, select:visible').not('#novoProduto');
  var check = false;
  $(fields).each(function (index, field) {
    var item = $(field).val();
    if (item == "") {
      bootbox.alert("Preencher todos os campos!");
      check = false;
      return false;
    }
    else {
      check = true;
    }
  });
  return check;
}

// funções de limpeza de tela

function clearForm(idForm) {
  $('#' + idForm).find('input').val('');
  $('#' + idForm).find('select').val('');
  $('#cnpj').attr('readonly', false);
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

function clearMesaStatus() {
  $('#sideBarMesas').find('input').val('')
  $('#cpf').attr('readonly', false);
}

// funções de manipulação do cliente

function cadastrarCliente() {

  var check = validateInputsAndSelectOption();

  if (check) {
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
          bootbox.alert("Cadastrado com sucesso!");
          clearForm("cadastroCliente");
        }
        else {
          bootbox.alert("Erro no cadastro!");

        }
      }
    });
  }
}

function alterarCliente() {

  var check = validateInputsAndSelectOption();

  if (check) {
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
          bootbox.alert("Alteração realizada com sucesso!");
        }
        else {
          bootbox.alert("Erro no processamento da alteração!");
        }
      }
    });
  }
}

function excluirCliente() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
          bootbox.alert("Exclusão realizada com sucesso!");
        }
        else {
          bootbox.alert("Erro no processamento da exclusão!");
        }
      }
    });
  }
}

function buscarCliente() {
  var cpf = $('#cpf').val();
  if (cpf != "") {
    $.ajax({
      url: 'Cadastro',
      method: 'GET',
      data: {
        'menu': 'BuscarCliente',
        'cpf': cpf,
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
        }
        else {
          bootbox.alert("CPF não encontrado!");

        }
      }
    });
  }
  else {
    bootbox.alert("Preencher todos os campos!");
  }
}

// funções de manipulação do funcionario

function buscarFuncionario() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
          $('#sexo option[value="'+data[0].sexo+'"]').prop('selected', true);
          $('#grupo option[value="'+data[0].grupo+'"]').prop('selected', true)
          $('#unidade option[value="'+data[0].unidade+'"]').prop('selected', true);
          $('#usuario').val(data[0].usuario);
          psw = data[0].senha;
        }
        else {
          bootbox.alert("CPF não encontrado!");
        }
      }
    });
  }
}

function cadastrarFuncionario() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
            bootbox.alert("Cadastrado com sucesso!");
            clearForm("cadastroFuncionario");
          }
          else {
            bootbox.alert("Erro no cadastro!");
          }
        }
      });
    }
    else {
      bootbox.alert("Senha e confirmação de senha diferentes. Inserir novamente.");
      $('#password').val('');
      $('#passwordConfirmation').val('');
    }
  }
}

function alterarFuncionario() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
              bootbox.alert("Alteração realizada com sucesso!");
            }
            else {
              bootbox.alert("Erro no processamento da alteração!");
            }
          }
        });
      }
      else {
        bootbox.alert("Senha e confirmação de senha diferentes. Inserir novamente.");
        $('#newPassword').val('');
        $('#newPasswordConfirmation').val('');
      }
    }
    else {

      bootbox.alert("Senha anterior incorreta. Inserir novamente.");
      $('#lastPassword').val('');
      $('#newPassword').val('');
      $('#newPasswordConfirmation').val('');
    }
  }
}

function excluirFuncionario() {
  var cpf = $('#cpf').val();
  if (cpf != "") {
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
          bootbox.alert("Exclusão realizada com sucesso!");
        }
        else {
          bootbox.alert("Erro no processamento da exclusão!");
        }
      }
    });
  }
  else {
    bootbox.alert("Preencher todos os campos!");
  }
}

// funções de manipulação de fornecedors

function buscarFornecedor() {
	var cnpj = $('#cnpj').val();
	    if (cnpj.length > 17) {
	      $.ajax({
	        url: 'Cadastro',
	        method: 'GET',
	        data: {
	          'menu': 'BuscarFornecedor',
	          'cnpj': cnpj,
	        },
	        success: function (data) {
	          if (data != null && data != "") {
	            data = JSON.parse(data);
	            $('#cnpj').attr('readonly', true);
	            $('#idFornecedor').val(data[0].id)
	            $('#alterarFornecedor').css("display", "");
	            $('#fornecedor').val(data[0].fornecedor);
	            $('#inputIcon').val(data[0].email);
	            $('#telefone').val(data[0].telefone);
	          }
	          else {
	            bootbox.alert("CNPJ não encontrado!");
	          }
	        }
	      });
	  }
}

function cadastrarFornecedor() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
          bootbox.alert("Cadastrado com sucesso!");
          clearForm("fornecedorCadastro");
        }
        else {
          bootbox.alert("Erro no cadastro!");
        }
      }
    });
  }
}

function alterarFornecedor() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
          bootbox.alert("Alteração realizada com sucesso!");
        }
        else {
          bootbox.alert("Erro no processamento da alteração!");
        }
      }
    });
  }
}

function excluirFornecedor() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
          bootbox.alert("Exclusão realizada com sucesso!");
        }
        else {
          bootbox.alert("Erro no processamento da exclusão!");
        }
      }
    });
  }
}

// funções de manipulação da comanda

function salvarComanda() {
  var check = validateInputsAndSelectOption();
  if (check) {
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
          bootbox.alert("Cadastro realizado com sucesso!");
          clearForm('comandaAdmin');
          clearComandaAdmin();
          $('#numComanda').attr('readonly', false);
          $('#cpf').attr('readonly', false);
          $('#buscarComanda').show();
        }
        else {
          bootbox.alert("Erro no cadastro!");
        }
      }
    });
  }
}

function atualizarComanda() {
	
  var check = validateInputsAndSelectOption();
  if (check) {
    var produtos = JSON.stringify(salvarProdutosDaComanda());

    $.ajax({
      url: 'Comanda',
      dataType: 'json',
      data: {
        'menu': 'AtualizarComanda',
        'cpf': $('#cpf').val(),
        'servico': $('#tpServico').val(),
        'jogo': $('#jogos').val(),
        'horas': $('#qtdHoras').val(),
        'numComanda': $('#numComanda').val(),
        'produtos': produtos
      },
      method: 'GET',
      success: function (data) {
        if (data == "true" || data == true) {
          bootbox.alert("Alteração realizada com sucesso!");
          clearForm('comandaAdmin');
          clearComandaAdmin();
          $('#numComanda').attr('readonly', false);
          $('#cpf').attr('readonly', false);
          $('#buscarComanda').show();
        }
        else {
          bootbox.alert("Erro na alteração!");
        }
      }
    });
  }
}

function abrirComanda() {

  clearComandaAdmin();
  $('#buscarComanda').removeClass('fechar');
  $('#buscarCliente, #addProduto').show();
  $('#totalFechamento, #totalFechamentoTextBox').hide();
  $('#tpServico, #jogos').attr('disabled', false);
  $('#qtdHoras').attr('readonly', false);
  $('H2').text('Abrir nova comanda');
  $('#encerrarForm').css('display', 'none');
  $('#salvarForm').css('display', '');

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
        $('#abrirComanda, #comandaAdmin, #btnInferiorComanda').css('display', "");
        $('.salvar,.editar,.excluir').css('display','');
      }
    }
  });
}

function alterarComanda() {
  clearComandaAdmin();
  $('.salvar,.editar,.excluir').css('display','');
  $('#buscarComanda').removeClass('fechar');
  $('#salvarForm, #encerrarForm, #totalFechamento, #totalFechamentoTextBox').css('display', 'none');
  $('#abrirComanda, #comandaAdmin, #btnInferiorComanda, #alterarForm').css('display', '');
  $('#buscarComanda,#addProduto').show();
  $('#numComanda').val('').attr('readonly', false);
  $('#buscarCliente').hide();
  $('#cpf').attr('readonly', true);
  $('H2').text('Alterar comanda');
  $('#tpServico, #jogos').attr('disabled', false);
  $('#qtdHoras').attr('readonly', false);
}

function buscarComandaCliente() {


  var idComanda = $('#numComanda').val();
  var table = $('#tableControleProdutos');
  var totalComanda = 0;

  if (idComanda != 0 && idComanda != "0" && idComanda != "" && !table.is(':visible')) {
    $.ajax({
      url: 'Comanda',
      method: 'GET',
      data: {
        'menu': 'BuscarComandaCliente',
        'idComanda': idComanda
      },
      success: function (data) {
        if (data != "" && data != undefined) {
          $('#numComanda').attr('readonly', true);
          data = JSON.parse(data);
          $(data).each(function (index, item) {
            if (index == 0) {
              $('#cpf').val(item.cpf);
              $('#nome').val(item.nome);
              $('#tpServico option[value="'+item.tipoServico+'"]').prop('selected', true);
              $('#jogos option[value="'+item.jogo+'"]').prop('selected', true)
              $('#qtdHoras').val(item.horasReservadas);
              table.css('display', "");
            }
            else {
              adicionarItemComanda();
              var linha = $("#produto" + index);
              linha.find('.idItemConsumo').val(item.idItemConsumo);
              linha.find('.quantidade').val(item.quantidadeItemConsumo).attr('readonly', true);
              linha.find('.idProduto').val(item.idProduto);
              linha.find('.produto').val(item.nomeItemConsumo).attr('readonly', true);
              linha.find('.preco').val(item.precoUnitario.toFixed(2).replace(".", ","));
              linha.find('.total').val((item.quantidadeItemConsumo * item.precoUnitario).toFixed(2).replace(".", ","));
              totalComanda += (item.quantidadeItemConsumo * item.precoUnitario);
            }
          });
          $('input.totalComanda').val(totalComanda.toFixed(2).replace(".", ","));
          var hasClass = $('#buscarComanda').hasClass('fechar');
          if(hasClass){
        	  $('.salvar,.editar,.excluir').css('display','none');
          }else{
        	  $('.salvar,.editar,.excluir').css('display','');
          }
        }
        else {
          bootbox.alert("Comanda não encontrada!")
        }
      }
    });
  }
  else {
    bootbox.alert("Preencher todos os campos!");
  }
}

//alimenta o combobox com todos os jogos ao carregar a pagina.

function loadComboBoxJogos() {
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
          $('#jogos').append('<option value=' + data[i].id + '>' + data[i].jogo + '</option>');
        }
      }
    }
  });
}

function loadComboboxProdutos() {
  $.ajax({
    url: 'Comanda',
    method: 'GET',
    data: {
      'menu': 'CarregarProdutos'
    },
    success: function (data) {
      if (data != "" && data != undefined) {
        data = JSON.parse(data);
        for (var i = 0; i < data.length; i++) {
          $('#produtos').append('<option value=' + data[i].id + '>' + data[i].Nome + '</option>');
        }
      }
    }
  });
}

function changeIdComboBox() {
  var tipo = $('#tipo').val();
  if (tipo == 2) {
    $('#cadastrarProduto select.change option').remove();
    $('#cadastrarProduto select.change').attr('id', 'jogos');
    loadComboBoxJogos();

  }
  else {
    $('#cadastrarProduto select.change option').remove();
    $('#cadastrarProduto select.change').attr('id', 'produtos');
    loadComboboxProdutos();
  }
}

//faz a renderização das mesas ao carregar a pagina.

function loadMesas() {

  $.ajax({
    url: 'Comanda',
    method: 'GET',
    data: {
      'menu': 'CarregarMesas'
    },
    success: function (data) {

      if (data != "" && data != undefined) {
        data = JSON.parse(data);
        $(data).each(function (index, item) {
          var tipo = item.tipo;
          var status = item.status;
          var itemNumber = item.numero > 9 ? item.numero : "0" + item.numero
          var imgId = "mesa" + itemNumber;
          if (tipo == 1) {
            if (status == 0) {
              $('#overviewMesas').append("<img id=" + imgId + " src='img/icon-table-green.PNG' class='smallTable'><span class='tableNumber'>" + itemNumber + "</span>");
            }
            else {
              $('#overviewMesas').append("<img id=" + imgId + " src='img/icon-table-red.PNG'class='smallTable selected'><span class='tableNumber'>" + itemNumber + "</span>");
            }
          }
          else {
            if (status == 0) {
              $('#overviewMesas').append("<img id=" + imgId + " src='img/icon-tableroom-green.PNG' class='bigTable'><span class='tableNumber'>" + itemNumber + "</span>");
            }
            else {
              $('#overviewMesas').append("<img id=" + imgId + " src='img/icon-tableroom-red.PNG' class='bigTable selected'><span class='tableNumber'>" + itemNumber + "</span>");
            }
          }
        });
      }
    }
  });
}

function buscarIdComandaNomeCliente() {
  var cpf = $('#cpf');
  if (cpf.val() != "") {
    $.ajax({
      url: 'Comanda',
      method: 'GET',
      data: {
        'menu': 'BuscarIdComandaNomeCliente',
        'cpf': cpf.val()
      },
      success: function (data) {
        if (data != "" && data != undefined) {
          data = JSON.parse(data);
          var item = data[0];
          cpf.attr('readonly', true);
          $('#idComanda').val(item.idComanda);
          $('#nome').val(item.nome);
          $('#numeroMesa').val(item.numeroMesa == 0 ? "" : item.numeroMesa);
        }
      }
    });
  }
  else {
    bootbox.alert("Preencher todos os campos!");
  }
}

function associarMesaAcomanda(idMesa, idComanda) {

  $.ajax({
    url: 'Comanda',
    method: 'GET',
    data: {
      'menu': 'AssociarMesaComanda',
      'idMesa': idMesa,
      'idComanda': idComanda
    },
    success: function (data) {
      if (data == "true" || data == true) {
        var idBody = $('body').attr('id');
        if (idBody == 'pageMesas') {
          bootbox.alert("Mesa " + idMesa + " reservada para comanda " + idComanda + " !");
        }
      }
      else {
        bootbox.alert("Erro ao tentar fazer reserva !");
      }
    }
  });
}

function removerMesaDeComanda(idComanda) {

  $.ajax({
    url: 'Comanda',
    method: 'GET',
    data: {
      'menu': 'RemoverMesaComanda',
      'idComanda': idComanda
    },
    success: function (data) {
      if (data == "true" || data == true) {
        var idBody = $('body').attr('id');
        if (idBody == 'pageMesas') {
          bootbox.alert("Mesa liberada !");
        }
      }
      else {
        bootbox.alert("Erro ao tentar liberada !");
      }
    }
  });
}

function isEmail(email) {
  var email = $('#'+email);
  if(email.val().length > 0){
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  if(!regex.test(email.val())){
		  bootbox.alert("e-mail inválido!");
		  email.val('');
	  }
  }
}

//**********FUNÇÃO PARA EXECUÇÃO APÓS LOAD DA PAGINA**************
$(document).ready(function () {
  var psw;

  //valida todos os campos de nome para receber apenas letras
  $('#nome').on('focusout', function(){
	 var nome = $(this).val();
	 if(nome.length > 0){
		 var regex = /[{3}a-zA-ZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ\s\'\-]{3}/;
		 if(!regex.test(nome)){
			 bootbox.alert("O nome digitado é inválido");
		 }
	 }
  });
  
  //valida todos os campos de nome para receber apenas letras
  $('#valor').on('focusout', function(){
	 var valor = $(this).val();
	 if(valor.length > 0){
		 var regex = /^[0-9]/;
		 if(!regex.test(valor)){
			 bootbox.alert("O valor digitado é inválido");
			 $(this).val('');
		 }
	 }
  });
  
  //insere mascaras nos campos descritos abaixo
  jQuery(function ($) {
    $("#cpf").mask("999.999.999-99");
    $('#dataNascimento').mask("99/99/9999");
    $('#telefone').mask("(99) 9999-9999?9");
    $('#cnpj').mask("99.999.999/9999-99");
    $('#qtdHoras').mask("99:99")
  });

  //adiciona um evento de double click na pagina de status de mesa
  $('body').on('dblclick', 'img[id*=mesa]', function (e) {
    var tableClicked = $(this);
    var inputMesa = $('#numeroMesa');
    var idMesa = parseInt(tableClicked.attr('id').replace('mesa0', '').replace('mesa', ''));
    var idComanda = $('#idComanda').val();
    if (idComanda != "") {
      if (!tableClicked.hasClass('selected')) {
        //requisição irá verificar se o cliente ja tem mesa associada a sua comanda
        $.ajax({
          url: 'Comanda',
          method: 'GET',
          data: {
            'menu': 'VerificarClientePossuiMesa',
            'idComanda': idComanda
          },
          success: function (data) {
            if (data != "true" && data != true) {
              associarMesaAcomanda(idMesa, idComanda);
              tableClicked.addClass('selected');
              if (tableClicked.hasClass('smallTable')) {
                tableClicked.attr('src', 'img/icon-table-red.PNG');
                inputMesa.val(idMesa);
              }
              else {
                tableClicked.attr('src', 'img/icon-tableroom-red.PNG');
                inputMesa.val(idMesa);
              }

            }
            else {
              bootbox.alert("Cliente já está associado a uma mesa !");
            }
          }
        });
      }
      else {
        removerMesaDeComanda(idComanda);
        tableClicked.removeClass('selected');
        if (tableClicked.hasClass('smallTable')) {
          tableClicked.attr('src', 'img/icon-table-green.PNG');
          inputMesa.val("");
        }
        else {
          tableClicked.attr('src', 'img/icon-tableroom-green.PNG');
          inputMesa.val("");
        }
      }
    }
  });

  // acrescenta mais um evento no botão buscar, quando
  $('#buscarCliente').on('click', function () {
    var cpf = $('#cpf').val();
    if (cpf != "") {
      $('#tableControleProdutos').css("display", "");
      $('#abrirComanda, #comandaAdmin, #btnInferiorComanda').css('display', "");
    }
  });

  $('#usuarioLogado li').on('click','a',function(){
	  setCookie('grupoUsuario',0,-1);
	  setCookie('usuarioAutenticado',0,-1);
  });
});

//altera o input de descricao no campo de cadastro de produto para um combobox
$('#novoProduto').on('click', function () {
  var isCheck = $(this).is(':checked');
  if (!isCheck) {
    $('#nome').hide();
    $('#cadastrarProduto select.change').show();
  }
  else {
    $('#nome').show();
    $('#cadastrarProduto select.change').hide();
  }
  
});

//****************************************************************
// **********/23/11/2016**************

function btnOptionFecharComanda() {
  $('H2').text('Fechar comanda');
  clearComandaAdmin();
  $('#alterarForm, #salvarForm, #addProduto').css('display', 'none');
  $('#abrirComanda, #comandaAdmin, #btnInferiorComanda, #encerrarForm, #totalFechamento, #totalFechamentoTextBox').css('display', '');
  $('#buscarComanda').show();
  $('#buscarComanda').addClass('fechar');
  $('#numComanda').val('').attr('readonly', false);
  $('#buscarCliente').hide();
  $('#cpf').attr('readonly', true);
  $('#tpServico, #jogos').attr('disabled', true);
  $('#nome, #qtdHoras').attr('readonly', true);
}

function fecharComanda() {
  var check = validateInputsAndSelectOption();
  if (check) {
    $.ajax({
      url: 'Comanda',
      dataType: 'json',
      data: {
        'menu': 'FecharComanda',
        'cpf': $('#cpf').val(),
        'numComanda': $('#numComanda').val(),
      },
      method: 'GET',
      success: function (data) {
        if (data == "true" || data == true) {
          bootbox.alert("Comanda encerrada!");
          btnOptionFecharComanda();
        }
        else {
          bootbox.alert("Erro no fechamento!");
        }
      }
    });
  }
}

var qtdProdutoEstoque;
function btnSalvarProduto(element) {
  var check = false;
  var elementX = $("#" + element.id);
  var idItemConsumo = elementX.find('.idItemConsumo').val();
  if (idItemConsumo != "" && idItemConsumo != 0) {
    alterarItemComanda(element);
    check = true;
  }
  else if(parseInt(elementX.find('.quantidade').val()) <= qtdProdutoEstoque){
    elementX.find('input:visible').each(function (index, item) {
      var itemValue = $(item).val();
      if (itemValue == "" || itemValue == undefined) {
        bootbox.alert("Preencha todos os campos!");
        check = false;
        return false;
      }
      else {
        check = true;
      }
    });
  }else{
	  bootbox.alert("Quantidade não disponível no estoque!! [Estoque Atual: " + qtdProdutoEstoque + " unidade(s).]");
	  elementX.find('.quantidade').val(qtdProdutoEstoque);
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
  }
  else {
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
    data: {
      'menu': 'AlterarItemConsumo',
      'idItemConsumo': idItemConsumo,
      'idComanda': idComanda,
      'quantidade': quantidade,
      'produto': idProduto
    },
    success: function (data) {
      if (data == true || data == "true") {
        bootbox.alert("Alterado com sucesso!");
      }
      else {
        bootbox.alert("Erro na alteração!");
      }
    }
  });
}

function excluirItemComanda(idItemConsumo) {
  $.ajax({
    url: 'Comanda',
    method: 'GET',
    data: {
      'menu': 'ExcluirItemConsumo',
      'idItemConsumo': idItemConsumo,
      'idComanda': $('#numComanda').val()
    },
    success: function (data) {
      if (data == true || data == "true") {
        bootbox.alert("Excluido com sucesso!");
      }
      else {
        bootbox.alert("Erro na exclusão!");
      }
    }
  });
}

var sequencia = 1;

function adicionarItemComanda() {
  var itemId = "produto" + sequencia;
  var trInit = "<tr id=" + itemId + ">";
  var th = "<th scope='row'>" + sequencia + "</th>";
  var td1 = "<td style='display:none'><input class='idItemConsumo' type='text'></td>";
  var td2 = "<td><input class='quantidade' type='text'></td>";
  var td3 = "<td style='display:none'><input class='idProduto' type='text'></td>";
  var td4 = "<td><input class='produto' type='text' onfocusout='verificaProdutoCadastrado(" + itemId + ")'></td>";
  var td5 = "<td><input class='preco' type='text'disabled></td>";
  var td6 = "<td><input class='total' type='text' disabled></td>";
  var td7Btn = "<td height='30px' width='50px'><a href='#' class='btn btn-success salvar' role='button' onclick='btnSalvarProduto(" + itemId + ")'>Salvar</a></td>"; // btn
  var td8Btn = "<td height='30px' width='50px'><a href='#' class='btn btn-warning editar' role='button' onclick='btnEditarProduto(" + itemId + ")'>Editar</a></td>"; // btn
  var td9Btn = "<td height='30px' width='50px'><a href='#' class='btn btn-danger excluir' role='button' onclick='btnRemoverProduto(" + itemId + ")'>Excluir</a></td>"; // btn
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
                    data = data[0];
                    if (data.nome != "null") {
                        element.find('.idProduto').val(data.id);
                        var valorProduto = parseInt(data.valor);
                        element.find('.preco').val(valorProduto.toFixed(2).replace(".", ","));
                        element.find('.total').val((valorProduto * qtd).toFixed(2).replace(".", ","));
                        if (parseInt(data.quantidade) < qtd) {
                        	qtdProdutoEstoque = data.quantidade;
                        	bootbox.alert("Quantidade não disponivel no estoque!! [Estoque Atual: " + data.quantidade + " unidade(s).]");
                        	element.find('.quantidade').val(data.quantidade);
                        }else{
                        	qtdProdutoEstoque = data.quantidade;
                        }
                    }
                    else {
                        bootbox.alert("Produto não cadastrado!");
                        element.find('.idProduto').val("");
                        element.find('.produto').val("");
                        element.find('.preco').val("");
                        element.find('.total').val("");
                        return false;
                    }
                }
            });
        }
        else {
            bootbox.alert("Preencha o campo de quantidade!");
            return false;
        }
    }
    else {
        element.find('.preco').val("");
        element.find('.total').val("");
    }
}

//function verificaProdutoCadastrado(idTextBox) {
//  var element = $('#' + idTextBox.id);
//  var produto = element.find('.produto').val();
//  var qtd = element.find('.quantidade').val();
//  if (produto != "" && produto.length > 2) {
//    if (qtd > 0) {
//      $.ajax({
//        url: 'Comanda',
//        data: {
//          'menu': 'VerificaProdutoCadastrado',
//          'produto': produto
//        },
//        method: 'GET',
//        success: function (data) {
//          data = JSON.parse(data);
//          if (data[0].nome != "null") {
//            element.find('.idProduto').val(data[0].id);
//            var valorProduto = parseInt(data[0].valor);
//            element.find('.preco').val(valorProduto.toFixed(2).replace(".", ","));
//            element.find('.total').val((valorProduto * qtd).toFixed(2).replace(".", ","));
//          }
//          else {
//            bootbox.alert("Produto não cadastrado!");
//            element.find('.idProduto').val("");
//            element.find('.produto').val("");
//            element.find('.preco').val("");
//            element.find('.total').val("");
//            return false;
//          }
//        }
//      });
//    }
//    else {
//      bootbox.alert("Preencha o campo de quantidade!");
//      return false;
//    }
//  }
//  else {
//    element.find('.preco').val("");
//    element.find('.total').val("");
//  }
//}

function salvarProdutosDaComanda() {
  var i = 0;
  var listaDeProdutos = [];
  var produtosInseridos = $('#tableControleProdutos tr')
  if (produtosInseridos.length > 0) {
    $(produtosInseridos).each(function (index, item) {
      var productLine = $(item);
      var idProduto = productLine.find('td .idProduto').val();
      var Quantidade = productLine.find('td .quantidade').val();
      var hasIdComanda = productLine.find('td .idItemConsumo').val();
      if (hasIdComanda == "" && Quantidade > 0) {
        var obj = {
          idProduto: idProduto,
          Quantidade: Quantidade
        };
        listaDeProdutos[i] = obj;
        i++;
      }
    });
    return listaDeProdutos;
  }
  return undefined;
}

function salvarItemEstoque() {
  var check = validateInputsAndSelectOption();
  if (check) {
    var isChecked = $('#novoProduto').is(':checked');
    var tipo = $('#tipo').val();
    var idFornecedor = $('#idFornecedor').val();
    var nome;
    var idItem;
    if (isChecked) {
    	nome = $('#nome').val();
    	idItem = 0;
    }
    else{
    	nome = $('select.change option:selected').text();
    	idItem = $('select.change option:selected').val();
    } 
    var quantidade = $('#quantidade').val();
    var valor = $('#valor').val().replace(',', '.');

    $.ajax({
      url: 'Cadastro',
      data: {
        'menu': 'CadastroItem',
        'idItem': idItem,
        'isChecked': isChecked,
        'tipo': tipo,
        'idFornecedor': idFornecedor,
        'quantidade': quantidade,
        'valor': valor,
        'nome': nome
      },
      method: 'GET',
      success: function (data) {
        if (data == "true" || data == true) {
          bootbox.alert("Cadastrado com sucesso!");
          clearForm('cadastrarProduto');
          $('#cnpj').attr('readonly', false);
        }
        else {
          bootbox.alert("Erro no cadastro!");
        }
      }
    });
  }
}

function excluirProdutoEstoque() {
  var tipo = $('#tipo').val();
  var idItem = $('select.change option:selected').val();
  if (tipo != "..." && idItem != "...") {
    $.ajax({
      url: 'Exclusao',
      data: {
        'menu': 'ExclusaoItemEstoque',
        'idItem': idItem,
        'tipo': tipo
      },
      method: 'GET',
      success: function (data) {
        if (data == "true" || data == true) {
          bootbox.alert("Excluido com sucesso!");
          clearForm('cadastrarProduto');
        }
        else {
          bootbox.alert("Erro na exclusão!");
        }
      }
    });
  }
  else {
    bootbox.alert("Preencher todos os campos!");
  }
}

function loginUsuario() {
  var user = $('#user').val();
  var psw = $('#psw').val();
  $.ajax({
    url: 'UserLogin',
    method: 'post',
    data: {
      'user': user,
      'psw': psw
    },
    success: function (data) {
      if (data == "false" || data == false) {
        bootbox.alert("Nome de usuário ou senha inválidos!");
      }
      else {
        var localhost = window.location.origin;
        var userCookieGroup = getCookie('grupoUsuario');
        if (userCookieGroup == 2) {
          window.location.replace("/GoldenAxePremiumBar/index.html");
        }
        else {
          window.location.replace("/GoldenAxePremiumBar/comandaAdminService.html");
        }
      }
    }
  });
}