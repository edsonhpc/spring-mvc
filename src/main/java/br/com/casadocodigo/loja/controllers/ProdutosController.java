package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

/**
 * 
 * @author Edson Cavalcanti
 * - Quando recebemos os dados do formulário o próprio Spring faz o que chamamos de bind dos parâmetros do método com os names do formuĺario.
 * - Cada retorno de view ele busca os arquivos jsp que estão dentro da pasta webapp/WEB-INF/views.
 * - @Autowired serve para injetar o ProdutoDAO, e usando-a não precisamos nos preocupar em criar manualmente esse objeto o Spring faz automaticamente.
 * - ModelAndView é uma forma de enviar dados objetos para a view
 */
@Controller
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@RequestMapping("/produtos")
	public String gravar(Produto produto) { 
		
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
}
