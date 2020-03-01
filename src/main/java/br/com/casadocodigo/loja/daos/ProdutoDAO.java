package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

/**
 * 
 * @author Edson Cavalcanti
 * - Classe ProdutoDAO o DAO (Data Access Object ou Objeto de Acesso a Dados)
 * - Para que está classe realize a persistência, é necessario criarmos um gerenciador de entidades, ou seja o EntityManager que é fornecido pelo Spring.
 * - O próximo passo é fazer com que o ProdutosController ao receber produto utilize o nosso DAO para salvar no banco de dados.
 * - @Repository usamos essa anotação para dizermos ao Spring gerenciar está classe.
 * - O passo seguinte é informar ao Spring para ele encontrar está classe e para isso entramos na classe de configuração AppWebConfiguration.
 *
 */

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}
}
