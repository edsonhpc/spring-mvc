package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Edson Cavalcanti
 * O Objetivo dessa classe é de relacionar o Produto do carrinho e seu respectivo preço.
 * Por ser uma classe de negócio deixamos ela no pacote de models, visto que ela não é um controller e nem uma view.
 * 
 *
 */

public class CarrinhoItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private TipoPreco tipoPreco;

	public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
		this.produto = produto;
		this.tipoPreco = tipoPreco;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}
	
	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}
	
	public BigDecimal getPreco() { // Esse método retorna o valor do preço de acordo com seu tipo que entra no List precos implementado na classe Produto
		return this.produto.precoPara(tipoPreco);
	}

	public BigDecimal getTotal(int quantidade) { // Retorna a multiplicação do método acima getPreco * quantidade do parametro.
		return this.getPreco().multiply(new BigDecimal(quantidade));
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (tipoPreco != other.tipoPreco)
			return false;
		return true;
	}
	
  
}
