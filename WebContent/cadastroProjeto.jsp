<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/cadastro.css">
<title>Cadastro de Projeto</title>
</head>
<body>
	<center>
		<h1>Cadastro de Projeto</h1>
		<h3 style="color:orange;">${msg}</h3>
	</center>
	<form action="salvarProjeto" method="post" id="formProjeto">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${projeto.id}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${projeto.nome}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Data início:</td>
						<td><input type="text" id="datainicio" name="datainicio"
							value="${projeto.datainicio}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Data previsão final:</td>
						<td><input type="text" id="dataprevisaofim" name="dataprevisaofim"
							value="${projeto.dataprevisaofim}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Data fim:</td>
						<td><input type="text" id="datafim" name="datafim"
							value="${projeto.datafim}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Descrição:</td>
						<td><input type="text" id="descricao" name="descricao"
							value="${projeto.descricao}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Status:</td>
						<td><input type="text" id="status" name="status"
							value="${projeto.status}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Orçamento:</td>
						<td><input type="text" id="orcamento" name="orcamento"
							value="${projeto.orcamento}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Risco:</td>
						<td><input type="text" id="risco" name="risco"
							value="${projeto.risco}" class="field-long" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"/> <input type="submit" onclick="document.getElementById('formProjeto').action = 'salvarProjeto?acao=reset'"  value="Cancelar"/></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption><h1>Projetos cadastrados</h1></caption>
			<tr>
				<td><h3>Id</h3></td>
				<td><h3>Nome</h3></td>
				<td><h3>Data início</h3></td>
				<td><h3>Data previsão final</h3></td>
				<td><h3>Data fim</h3></td>
				<td><h3>Descrição</h3></td>
				<td><h3>Status</h3></td>
				<td><h3>Orçamento</h3></td>
				<td><h3>Risco</h3></td>
				<td><h3>Excluir</h3></td>
				<td><h3>Alterar</h3></td>
			</tr>
			<c:forEach items="${projetos}" var="projeto">
				<tr>
					<td data-title="id"><c:out value="${projeto.id}" /></td>
					<td data-title="nome"><c:out value="${projeto.nome}" /></td>
					<td data-title="datainicio"><c:out value="${projeto.datainicio}" /></td>
					<td data-title="dataprevisaofim"><c:out value="${projeto.dataprevisaofim}" /></td>
					<td data-title="datafim"><c:out value="${projeto.datafim}" /></td>
					<td data-title="descricao"><c:out value="${projeto.descricao}" /></td>
					<td data-title="status"><c:out value="${projeto.status}" /></td>
					<td data-title="orcamento"><c:out value="${projeto.orcamento}" /></td>
					<td data-title="risco"><c:out value="${projeto.risco}" /></td>
					<td><a href="salvarProjeto?acao=delete&Projeto=${projeto.id}"><img src="resources/img/excluir.png" width="20px" height="20px" alt="excluir" title="Excluir" <c:if test="${(projeto.status=='INICIADO') or (projeto.status=='EM_ANADAMENTO') or (projeto.status=='ENCERRADO')}">style="visibility:hidden"</c:if>></a></td>
					<td><a href="salvarProjeto?acao=editar&Projeto=${projeto.id}"><img src="resources/img/editar.png" width="20px" height="20px" alt="editar" title="Editar" <c:if test="${projeto.status=='INICIADO'}">style="visibility:hidden"</c:if>></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>