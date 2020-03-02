<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulário de Cadastro de Novos Livros</title>
</head>
<body>
	
	<form action="/casadocodigo/produtos" method="POST">
	
		<div>
			<label> Título</label>
			<input type="text" name="titulo">
		</div>
		
		<div>
			<label>Descrição</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
		</div>
		
		<div>
			<label>Páginas</label>
			<input type="text" name="paginas">
		</div>
	
	  <!-- Com o forEach pegamos o objeto tipos atribui para tipoPreco e varStatus serve como um contador -->
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<input type="text"   name="precos[${status.index}].valor"> <!-- Com o indice eu acesso o value para envio ao Controller -->
				<input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}">
				<!-- No input eu preciso devolver o valor para controller precos é um atributo definido na classe Produto -->
			</div>
		
		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form>
	
</body>
</html>