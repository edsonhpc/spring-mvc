package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Edson Cavalcanti
 * - @Entity é usada para definirmos que está classe é uma entidade.
 * - Depois desse mapeamento o próximo passo é definir a classe de acesso a dados responsável por manipular os produtos, chamarei de ProdutoDAO.
 * - @ElementCollection com essa anotação indicamos que o atributo é uma coleção de elementos
 * - O Spring irá detectar que estamos enviando valores para o atributo precos em determindas posições "indice" da lista.
 * - Ele também vai detectar os valores que são do tipoPreco e irá preencher correramente a lista.
 * - precos[numero].tipo, estamos acessando o atributo tipo de um objeto do tipo Preco, e esse tipo é recuperado de um enum TipoPreco
 * - o input no form do tipo hidden é para passarmos o tipo de preço: ebook, impresso e combo
 * - @DateTimeFormat o objetivo dessa anotação é fazer com que o campo text que vem do form seja formatado para o tipo Calenda
 * - e no banco de dados será criado um campo do tipo DateTime
 * - No DateTimeFormat também poderia usar esse pattern="dd/MM/yyyy" direto no atributo, no entanto, teria que fazer toda aplicação
 * - por esse motivo realizei a configuração de um Bean na classe AppWebConfiguration com o método FormattingConversionService.
 */
@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	
	@Lob
	private String descricao;
	private int paginas;

	@DateTimeFormat
	private Calendar dataLancamento;
	
	@ElementCollection
	private List<Preco> precos;
	
	private String sumarioPath;
	
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

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public List<Preco> getPrecos() {
		return precos;
	}
	

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public String getSumarioPath() {
		return sumarioPath;
	}
	
	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
