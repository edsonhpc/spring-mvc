package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;

/**
 * 
 * @author Edson Cavalcanti
 * - O objetivo dessa classe é de receber a finalização do pagamento, visto que essa lógica é diferente do carrinho.
 * - O método finalizar não precisa de nenhum parametro visto que ele precisa somente do carrinho de compras que se encontra no escopo da sessão.
 * - A classe RestTemplate nos possibilita a fazer requisições, com o método postForObject passamos a utl, o corpo da mensagem e por último qual classe vamos receber a resposta.
 * 
 * - Callable
 *   - A implementação desse objeto tem por objetivo fazer com que a requisição seja feita de forma assincrona e que somente este usuário
 *  aguarda a resposta do processamento, dessa forma os demais usuários continuam usando a aplicação sem nenhum problema.
 *
 */

@Controller
@RequestMapping("/pagamento")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho; // Injeto carrinho que consta no escopo da sessão
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "finalizar", method = RequestMethod.POST)
	private Callable<ModelAndView> finalizar(RedirectAttributes model) {
		
		return () -> { // Cria o objeto do mesmo tipo esperado pelo retorno do método
		  
			String uri = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(response);
				model.addFlashAttribute("sucesso", "Pagamento Realizado com Sucesso");
				carrinho.limpaCarrinho();
				return new ModelAndView("redirect:/produtos");
				
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falha","Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}
}
