package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author Edson Cavalcanti
 * - Classe de configuração do Servlet do Spring MVC para que ele atenda as requisições recebidas pelo servidor.
 * - getServletFilters esse método do Spring espera receber um array de filtros, então criamos um CharacterEncodingFilter e definimos o encoding.
 * - 
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
	
	@Override
	protected Filter[] getServletFilters() { // Retorno um array com as configurações do filtro das requisições.
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		
		return new Filter[] {encodingFilter};
	}

}
