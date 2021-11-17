<%@page import="br.com.crud.model.ModelFuncionario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	
	
	<title>CRUD - Java Web: Cadastro de funcionários</title>
	
	 <style>
	.positionAbsolute {
		position: absolute;
		top: 100px;
		left: 150px;
		border-width: 5px;
		border-style: solid;
		border-color: red;
		background-color: rgb(167, 192, 246);
	}
	.msg {
		text-align: center;
		font-size: 15px;
		color: #ff0000;
		background-color: #fff3cd;
		border-color: #ffecb5;
	}
	</style>

	
	
</head>
<body>




<div class="positionAbsolute">
	<h6 align="center" class="sub-title">Cadastro de Funcionário</h6>

					<form class="form-material"	action="<%= request.getContextPath() %>/GerenciarFuncionario"	method="post" id="formUser" autocomplete="off">
					
						 <input type="hidden" name="acao" id="acao" value="">
						 <input type="hidden" value="<%=request.getParameter("url")%>" name="url">
					
						<div class="form-group form-control-sm">
																
								<div class="form-floating">
								 <input type="text" name="id" id="id" class="form-control form-control-sm"  readonly="readonly" value="${modolFuncionario.funcionarioId}">
								 <label for="floatingInput">ID:</label>
								</div>

						</div>
						<div class="form-group form-default">

						<div class="form-floating">
						  <input type="text" name="nome" class="form-control inputstl" id="nome" placeholder="Seu nome aqui" required="required"  value="${modolFuncionario.funcionarioNome}">
						  <label for="floatingInput">Nome:</label>
						</div>

						</div>
						
						<div class="form-group form-default">
							<div class="form-floating">
						  	<input type="text" name="idade" class="form-control inputstl" id="idade" placeholder="sua idade aqui" required="required"  value="${modolFuncionario.funcionarioIdade}">
						  	<label for="floatingInput">Idade:</label>
							</div>
						</div>
						
						<div class="form-group form-default">
							<div class="form-floating">
						  	<input type="text" name="sexo" class="form-control inputstl" id="sexo" placeholder="Seu sexo aqui" required="required"  value="${modolFuncionario.funcionarioSexo}">
						  	<label for="floatingInput">Sexo:</label>
							</div>
						</div>						
						
						<div class="form-group form-default">
													
							<div class="form-floating">
							<input type="email" name="email" id="email" class="form-control inputstl" required="required" placeholder="seu@email.com" autocomplete="off"  value="${modolFuncionario.funcionarioEmail}">
							<label for="floatingInput">Email</label>
							</div>
							

						</div>
						<div class="form-group form-default">
														
							<div class="form-floating">
							  <input type="text" name="profissao" class="form-control inputstl" id="profissao" placeholder="Sua profissão aqui" autocomplete="off" required="required"  value="${modolFuncionario.funcionarioProfissao}">
							  <label for="floatingInput">Profissão:</label>
							  
							</div>

						</div>
					
						<h6>&nbsp;</h6>	
						<div align="center" >
							
							<button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
							<button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
							<button type="button" class="btn btn-danger waves-effect waves-light" onclick="criarDelete();">Excluir</button>
							<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModalUsuario">Pesquisar</button>

						</div>
															
					</form>
					<div class="msg">
							<span id="msg">${msg}</span>
						</div>
</div>
</body>


<!-- Modal -->
<div class="modal fade" id="exampleModalUsuario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Funcionário]</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">

			<div class="input-group mb-3">
				<input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" aria-describedby="button-addon2">
						
				<button class="btn btn-primary waves-effect waves-light" type="button" id="button-addon2" onclick="buscarUsuario();">Buscar</button>
			</div>
		<div style="height: 300px;overflow: scroll;">
			<table class="table" id="tabelaresultados">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Nome</th>
			      <th scope="col">Ver</th>
			    </tr>
			  </thead>
			  
			  <tbody>
			  </tbody>
			  
			</table>
			
		</div>
		<span id="totalResultados"></span>
			</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>

<!-- End Modal -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>

<script type="text/javascript">

//Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()


function limparForm(){
	var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/
	
	for (p = 0; p < elementos.length; p++){
		elementos[p].value = '';
	}
	
}

function buscarUsuario(){
	var nomeBusca = document.getElementById('nomeBusca').value;
	
		var urlAction = document.getElementById('formUser').action;
		
		$.ajax({
			
			method: "get",
			url : urlAction,
			data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
			success: function (response){
				
			var json = JSON.parse(response);
			
			// console.info(json); /*informa internamente no navegador a lista (CONTROL+SHIFT+J) para mostrar*/
				
			$('#tabelaresultados > tbody > tr').remove();
			
			for(var p = 0; p < json.length; p++){
				
				$('#tabelaresultados > tbody').append('<tr> <td>'+json[p].funcionarioId+'</td><td> '+json[p].funcionarioNome+' </td> <td> <button onclick="verEditar('+json[p].funcionarioId+')" type="button" class="btn btn-info">Ver</button> </td> </tr>');
			}
			
			document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
			
			}
			
		}).fail(function(xhr, status, errorThrown){
			alert('Erro ao buscar usuário por nome: '+ xhr.responseText);
			
		});	
}

function criarDelete(){
	
	if (document.getElementById("id").value == "") {
        alert("Carregue o usuario para deletar!");
    }else if(confirm("Deseja realmente excluir os dados?")){
		document.getElementById("formUser").method = 'get';
		document.getElementById("acao").value = 'deletar';
		document.getElementById("formUser").submit();
    }
}

function verEditar(id){
	
	var urlAction = document.getElementById('formUser').action;
	
	window.location.href = urlAction + '?acao=buscarEditar&id='+id;
}

</script>
</html>