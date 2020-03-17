<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Produtos</title>
</head>
<body>
	<h1> Lista de Produtos</h1>
	
	<div>${sucesso}</div> <!-- Exibo a mensagem do redirectAttributes após o post -->
	<div>${falha}</div>
	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Paginas</td>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td> <!-- mvcUrl passamos o link para o "PC" ProdutosController# chamadno o método detalhe, o arg é para informar qual o 
						  parametro que estamos enviando para o controller, é obrigatório colocar como 0 no inicio e depois o -->
					<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">
						${produto.titulo}
					</a>
				</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>	
		</c:forEach>
	</table>
	
</body>
</html>