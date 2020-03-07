package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

/**
 * 
 * @author Edson Cavalcanti
 * - Quando recebemos os dados do formulário o próprio Spring faz o que chamamos de bind dos parâmetros do método com os names do formuĺario.
 * - Cada retorno de view ele busca os arquivos jsp que estão dentro da pasta webapp/WEB-INF/views.
 * - @Autowired serve para injetar o ProdutoDAO, e usando-a não precisamos nos preocupar em criar manualmente esse objeto o Spring faz automaticamente.
 * - ModelAndView é uma forma de enviar dados objetos para a view, ele faz um relacionamento de um modelo (model) com uma visualização (view).
 * - O method significa que estamos enviando o método de requisição do HTTP, objetivo é ter o mesmo mapeamento com funcionalidades distintas.
 * - Flash Scoped é um escopo onde os objetos que adicionamos nele através do método addFlashAttributes ficam vivos de um request 
 *   p/ outro enquanto o navegador executa um redirect (usando o código de status 302).
 * - No Spring sempre que retornamos uma String, por padrão ele vai procurar uma página jsp com o nome da String retornada.
 * - Redirect: passa um status o status 302 para que o navegador carregar uma outra página e esquecer a requisição anterior.
 * - RedirectAttributes é um recurso do Spring que permite enviar informações entre recursos.
 * - addFlashAttribute esse atributo só duram até a proxima requisição, ou seja, após efetuar o transporte de uma requisição a outra eles deixam de existir.
 * - A prática de fazer redireciomaneto após um post é conhecida como Always redirect after post("Sempre redirecione após post")
 */
@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(Produto produto, RedirectAttributes redirectAtributes) { 
		produtoDao.gravar(produto);
		redirectAtributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!"); // Objeto recebe a chave e valor 
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}
	
	
}
