package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Edson Cavalcanti
 * - @Entity é usada para definirmos que está classe é uma entidade.
 * - Depois desse mapeamento o próximo passo é definir a classe de acesso a dados responsável por manipular os produtos, chamarei de ProdutoDAO.
 * - 
 * 	
 *
 */
@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String descricao;
	private int paginas;

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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}
}
