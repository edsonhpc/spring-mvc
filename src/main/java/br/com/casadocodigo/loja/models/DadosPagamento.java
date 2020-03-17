package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

/**
 * 
 * @author Edson Cavalcanti
 * - Objetivo dessa é auxiliar o envio do valor para API externa de pagamentos, lembrando que o nome do atributo é exatamente 
 * igual ao que API espera receber.
 * - Isso é necessário prque o Spring irá transformar o objeto dessa classe em um objeto JSON, e por padrão ele irá criar a chave com o nome
 * do atributo da classe e o valor seŕa o mesmo do definido no atributo. 
 *
 * - Para esse tipo de serialização é nessário instalar o jackson como dependencia ele se encarrega de converter em Json.
 */
public class DadosPagamento {

	private BigDecimal value;

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}
	
	public DadosPagamento() {
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
}
