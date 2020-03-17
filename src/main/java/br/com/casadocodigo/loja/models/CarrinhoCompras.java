package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author Edson Cavalcanti
 * - O objetivo dessa classe é de criar o próprio Carrinho de Compras.
 * - Essa classe recebe o CarrinhoItem, por sua vez é adicionado no CarrinhoCompra
 * - O Map que criamos é usado para receber um item do CarrinhoItem a adicionar + 1
 * 
 * - Importante
 *   - apesar de estar usando o containsKey não é o suficiente, pois ele usa o método equals disponivel na classe Object
 *   - para que ele consiga comparar corretamente os itens da lista, devemos sobreescrever os dois métodos na classe CarrinhoItem
 *   - e depois na classe Produto para gerar os equals hash code.  
 *   
 * - Quando anotamos uma classe com @Component por padrão estamos transformando em um Bean do Spring, e também estamos 
 * configurando que este objeto será Singleton. Por padrão o Spring tem esta configuração para os seus componentes.
 *
 * - TARGET_CLASS - esse propriedade fará com que o Spring crie um proxy envolvendo o objeto alvo (TARGET_CLASS) afim de possibilitar
 * que as informações possam ser transitadas de um escopo para o outro. Eu pderia escolhar se caso achar que seja melhor posso usar
 * o SCOPE_REQUEST que também funciona nesse contexto.
 *
 */

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, // Anotação que define o escopo da aplicação
	   proxyMode = ScopedProxyMode.TARGET_CLASS  
	   ) 
public class CarrinhoCompras implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

    public Integer getQuantidade(CarrinhoItem item) { // Método que verifica se a quantidade já existe na lista e caso exite soma +1
	    if(!itens.containsKey(item)) { // O constains verifica se a lista tem a chave que no caso é o item do carrinho
	    	itens.put(item,0);
	    }
		return itens.get(item);
	}
	
	public int getQuantidade() { // Esse método itera sobre a nossa lista e retorna o total que tem no carrinho
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
	
	public Collection<CarrinhoItem> getItens(){
		return this.itens.keySet(); // Retorna a chave do mapa itens ou seja o item.
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item)); // Retorna o total do carrinho item
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (CarrinhoItem item : itens.keySet()) { // Percorre o itens pegando a chave e somando o total.
			total = total.add(getTotal(item)); 
		}
		return total;
	}
	
	public void remover(Integer produtoId, TipoPreco tipoPreco) { // Método que remove o produto da lista de compras.
		Produto produto = new Produto();  // Como a lista de compras é um objetivo do tipo CarrinhoItem precisei criar um objeto com mesmo tipo.
		produto.setId(produtoId);
		itens.remove(new CarrinhoItem(produto, tipoPreco));
	}
	
	public void limpaCarrinho() {
		itens.clear();
	}
	
}
