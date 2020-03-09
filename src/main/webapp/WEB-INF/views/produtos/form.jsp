<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulário de Cadastro de Novos Livros</title>
</head>
<body>
	<!-- mvcUrl gera uma URL de acordo com o controller, passando somente suas iniciais PC = ProdutoControler + método que será enviado os dados -->
	<form:form action="${s:mvcUrl('PC#gravar').build()}" method="POST" commandName="produto"> <!-- commandName usado para referenciar qual entidade do formulário -->
	
		<div>
			<label> Título</label>
			<input type="text" name="titulo">
			<form:errors path="titulo"/> <!-- tag form:errors exibe a mensagem de erro e o atributo path indica qual o atributo que queremos objet a mensagem -->
		</div>
		
		<div>
			<label>Descrição</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
			<form:errors path="descricao"/>
		</div>
		
		<div>
			<label>Páginas</label>
			<input type="text" name="paginas">
			<form:errors path="paginas"/>
		</div>
	
	  <!-- Com o forEach pegamos o objeto tipos atribui para tipoPreco e varStatus serve como um contador -->
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<input type="text"   name="precos[${status.index}].valor"> <!-- Com o indice eu acesso o value e envio ao Controller -->
				<input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}">
				<!-- No input eu preciso devolver o valor para controller, precos é um atributo definido na classe Produto -->
			</div>
		
		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form:form>
	
</body>
</html>