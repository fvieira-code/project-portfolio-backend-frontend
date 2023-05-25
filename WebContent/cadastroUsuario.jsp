<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/cadastro.css">
<title>Cadastro de usuário</title>
</head>
<body>
	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color:orange;">${msg}</h3>
	</center>
	<form action="salvarUsuario" method="post" id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Status:</td>
						<td><input type="text" id="status" name="status"
							value="${user.status}" class="field-long" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"/> <input type="submit" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"  value="Cancelar"/></td>
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
				<td><h3>Login</h3></td>
				<td><h3>Nome</h3></td>
				<td><h3>Status</h3></td>
				<td><h3>Excluir</h3></td>
				<td><h3>Alterar</h3></td>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td data-title="id"><c:out value="${user.id}" /></td>
					<td data-title="login"><c:out value="${user.login}" /></td>
					<td data-title="nome"><c:out value="${user.nome}" /></td>
					<td data-title="status"><c:out value="${user.status}" /></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/excluir.png" width="20px" height="20px" alt="excluir" title="Excluir" <c:if test="${user.status=='false'}">style="visibility:hidden"</c:if>></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/editar.png" width="20px" height="20px" alt="editar" title="Editar" <c:if test="${user.status=='false'}">style="visibility:hidden"</c:if>></a></td>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>