package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
/**
 * 
 * @author Edson Cavalcanti
 * - O objetivo de classe preço é fazer com que tenhamos uma lista de preços associados ao mesmo produto.
 * - @Embeddable com essa anotação é para o Spring portar os elementos de preço para dentro da coleção de elementos.
 * - O TipoPreco é um enum que definimos para que tenhamos os valores fixos.
 */

@Embeddable
public class Preco {

	private BigDecimal valor;
	private TipoPreco tipo;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}

}
