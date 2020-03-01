package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

/**
 * 
 * @author Edson Cavalcanti
 * - Quando recebemos os dados do formulário o próprio Spring faz o que chamamos de bind dos parâmetros do método com os names do formuĺario.
 * - Cada retorno de view ele busca os arquivos jsp que estão dentro da pasta webapp/WEB-INF/views.
 * - @Autowired serve para injetar o ProdutoDAO, e usando-a não precisamos nos preocupar em criar manualmente esse objeto o Spring faz automaticamente.
 *
 */
@Controller
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/produtos/form")
	public String form() {
		
		return "produtos/form";
	}
	
	@RequestMapping("/produtos")
	public String gravar(Produto produto) { 
		
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
}
