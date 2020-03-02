package br.com.casadocodigo.loja.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Edson Cavalcanti
 * - @Entity é usada para definirmos que está classe é uma entidade.
 * - Depois desse mapeamento o próximo passo é definir a classe de acesso a dados responsável por manipular os produtos, chamarei de ProdutoDAO.
 * - @ElementCollection com essa anotação indicamos que o atributo é uma coleção de elementos
 * - O Spring irá detectar que estamos enviando valores para o atributo precos em determindas posições da lista.
 * - Ele também vai detectar os valores que são do tipoPreco e irá preencher correramente a lista.
 * - precos[numero].tipo, estamos acessando o atributo tipo de um objeto do tipo Preco, e esse tipo é recuperado de um enum TipoPreco
 * - o input no form do tipo hidden é para passarmos o tipo de preço: ebook, impresso e combo
 */
@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String descricao;
	private int paginas;

	@ElementCollection
	private List<Preco> precos;
	
	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public List<Preco> getPrecos() {
		return precos;
	}
	
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}
}
