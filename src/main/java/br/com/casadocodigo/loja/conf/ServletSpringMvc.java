package br.com.casadocodigo.loja.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author Edson Cavalcanti
 * - Classe de configuração do Servlet do Spring MVC para que ele atenda as requisições recebidas pelo servidor.
 *
 */

public class ServletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
				AppWebConfiguration.class, // Referencia a classe de configuração Web
				JPAConfiguration.class}; // Classe de configurações ao banco de dados com JPA 
	}

	@Override
	protected String[] getServletMappings() { // Array com os mapeamentos que o Servlet irá atender.
		return new String[] {"/"}; // Dessa forma o Servlet atenderá as requisições a partir da raiz do projeto.
	}

}
