<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/cadastro.css">
<title>Cadastro de Pessoa</title>
</head>
<body>
	<center>
		<h1>Cadastro de pessoa</h1>
		<h3 style="color:orange;">${msg}</h3>
	</center>
	<form action="salvarPessoa" method="post" id="formPessoa">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${pessoa.id}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${pessoa.nome}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Data nascimento:</td>
						<td><input type="text" id="datanascimento" name="datanascimento"
							value="${pessoa.datanascimento}" class="field-long" /></td>
					</tr>
					<tr>
						<td>CPF:</td>
						<td><input type="text" id="cpf" name="cpf"
							value="${pessoa.cpf}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Funcionário:</td>
						<td><input type="text" id="funcionario" name="funcionario"
							value="${pessoa.funcionario}" class="field-long" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"/> <input type="submit" onclick="document.getElementById('formPessoa').action = 'salvarPessoa?acao=reset'"  value="Cancelar"/></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption><h1>Usuários cadastrados</h1></caption>
			<tr>
				<td><h3>Id</h3></td>
				<td><h3>Nome</h3></td>
				<td><h3>Data nascimento</h3></td>
				<td><h3>CPF</h3></td>
				<td><h3>Funcionário</h3></td>
				<td><h3>Excluir</h3></td>
				<td><h3>Alterar</h3></td>
			</tr>
			<c:forEach items="${pessoas}" var="pessoa">
				<tr>
					<td data-title="id"><c:out value="${pessoa.id}" /></td>
					<td data-title="nome"><c:out value="${pessoa.nome}" /></td>
					<td data-title="datanasimento"><c:out value="${pessoa.datanascimento}" /></td>
					<td data-title="cpf"><c:out value="${pessoa.cpf}" /></td>
					<td data-title="funcionario"><c:out value="${pessoa.funcionario}" /></td>
					<td><a href="salvarPessoa?acao=delete&pessoa=${pessoa.id}"><img src="resources/img/excluir.png" width="20px" height="20px" alt="excluir" title="Excluir" <c:if test="${pessoa.funcionario=='false'}">style="visibility:hidden"</c:if>></a></td>
					<td><a href="salvarPessoa?acao=editar&pessoa=${pessoa.id}"><img src="resources/img/editar.png" width="20px" height="20px" alt="editar" title="Editar" <c:if test="${pessoa.funcionario=='false'}">style="visibility:hidden"</c:if>></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>