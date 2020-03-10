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
			<form:input path="titulo"/> <!-- tag form:input é gerenciado pelo spring que deixa o código limpo e também não apaga o campo quando apresenta erro de validação no form -->
			<form:errors path="titulo"/> <!-- tag form:errors exibe a mensagem de erro e o atributo path indica qual o atributo que queremos objet a mensagem -->
		</div>
		
		<div>
			<label>Descrição</label>
			<form:textarea path="descricao" rows="10" cols="20"/>
			<form:errors path="descricao"/>
		</div>
		
		<div>
			<label>Páginas</label>
			<form:input path="paginas"/>
			<form:errors path="paginas"/>
		</div>
		
		<div>
			<label>Data de Lançamento</label>
		    <form:input path="dataLancamento"/>
		    <form:errors path="dataLancamento"/>
		</div>
	
	  <!-- Com o forEach pegamos o objeto tipos atribui para tipoPreco e varStatus serve como um contador -->
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor"/> <!-- Com o indice eu acesso o value e envio ao Controller -->
				<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/> <!-- No input eu preciso devolver o valor para controller, precos é um atributo definido na classe Produto -->
			</div>
		
		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form:form>
	
</body>
</html>