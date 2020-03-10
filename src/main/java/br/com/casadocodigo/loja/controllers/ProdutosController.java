package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

/**
 * 
 * @author Edson Cavalcanti
 * - Quando recebemos os dados do formulário o próprio Spring faz o que chamamos de bind dos parâmetros do método com os names do formuĺario.
 * - Cada retorno de view ele busca os arquivos jsp que estão dentro da pasta webapp/WEB-INF/views.
 * - @Autowired serve para injetar o ProdutoDAO, e usando-a não precisamos nos preocupar em criar manualmente esse objeto o Spring faz automaticamente.
 * - ModelAndView é uma forma de enviar dados objetos para a view, ele faz um relacionamento de um modelo (model) com uma visualização (view).
 * - O method significa que estamos enviando o método de requisição do HTTP, objetivo é ter o mesmo mapeamento com funcionalidades distintas.
 * - Flash Scoped é um escopo onde os objetos que adicionamos nele através do método addFlashAttributes ficam vivos de um request -> 
 * p/ outro enquanto o navegador executa um redirect (usando o código de status 302).
 * - No Spring sempre que retornamos uma String, por padrão ele vai procurar uma página jsp com o nome da String retornada.
 * - Redirect: passa um status o status 302 para que o navegador carregar uma outra página e esquecer a requisição anterior.
 * - RedirectAttributes é um recurso do Spring que permite enviar informações entre recursos.
 * - addFlashAttribute esse atributo só duram até a proxima requisição, ou seja, após efetuar o transporte de uma requisição a outra eles deixam de existir.
 * - A prática de fazer redireciomaneto após um post é conhecida como Always redirect after post("Sempre redirecione após post")
 * 
 * - Validation: utilizando o Validator do Spring
 * - O método initBinder é necessário para que seja a possivel a validação automatica.
 * - Com o método initBinder ele recebe um objeto do tipo WebDataBinder, esse objeto tem um método addValidators que recebe uma instância de uma classe
 *   - que implemente a interface Validador do Spring. 
 *   - Esse binder é o responsável por conecetar as duas coisas, por exemplo os dados do formulário com o objeto da classe Produto.
 *   - Implementar na classe ProdutoValitation a interface Validator;
 * - BindingResult recebe o resultado da verificação, esse objeto tem o método hasErrors que informa se houve erros de validação ou não.
 *   - O BindingResult precisa ser usado logo após a declaração do @Valid
 *   
 * - No método form passamos que o objeto Produto, isso é para que o objeto fique disponivel no form, caso não passemos a classe 
 * - como parâmetro será aprensentado erro no form, visto que o Spring tanta usar um objeto que não pode exibir no formulário.  
 */
@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@InitBinder
	public void initBinder (WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAtributes) { // Seguir ordem dos parâmetros
		
		if(result.hasErrors()) {
			return form(produto); // Verifica a existencia de erros de validação e retorna para o form();
		}
		
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
