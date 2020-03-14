package br.com.casadocodigo.loja.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

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
 */

@Component
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	private Integer getQuantidade(CarrinhoItem item) { // Método que verifica se a quantidade já existe na lista e caso exite soma +1
	    if(!itens.containsKey(item)) { // O constains verifica se a lista tem a chave que no caso é o item do carrinho
	    	itens.put(item,0);
	    }
		return itens.get(item);
	}
	
	public int getQuantidade() { // Esse método itera sobre a nossa lista e retorna o total que tem no carrinho
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
}
