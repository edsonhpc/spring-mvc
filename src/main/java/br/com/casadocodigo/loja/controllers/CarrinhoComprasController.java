package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
/**
 * 
 * @author Edson Cavalcanti
 * - O objetivo dessa classe é atender as nossas requisições do carrinho
 * - Método criarItem , passamos o produtoId e o tipo de preço selecionado no form, ele retorno o objeto carrinhoItem 
 * 
 * - Explicação
 * 	 1) Escopo de aplicação = desde que o servidor é iniciado apenas um objeto na memória é manipulado, o causa 
 *      conflito quando temos mais de um usuário usando a aplicação.
 *   2) Escopo de sessão, o objeto é criado para cada usuário que se conecta à aplicação, ou seja, usuários
 *      diferentes objetos diferentes, o que é ideal para um carrinho de compras, visto que cada usuário tem seu proprio carrinho.
 *   3) Escopo de request, no qual cada vez que acessamos a página, um objeto é criado.
 *
 * - O método remover faz um chamada para o método remover da classe CarrinhoCompras, passando id do produto e seu tipo.
 */

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}
		
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criarItem(produtoId, tipoPreco);
		
		carrinho.add(carrinhoItem);
		return modelAndView;
	}

	private CarrinhoItem criarItem(Integer produtoId, TipoPreco tipoPreco) { 
		Produto produto = produtoDao.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}
	
	
	
}
