package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;

/**
 * 
 * @author Edson Cavalcanti
 * - Essa classe tem a finalidade de informar ao Spring o diretório onde estaram as views do projeto.
 * - Objetivo de criar a pasta WEB-INF é de proteger os arquivos para não serem acessados diretamente sem passar pelo controller.
 * - InternalResourceViewResolver = Resolvedor Interno de Recursos View que ajuda o Spring a encontrar as views.
 * - @Bean é uma anotação de configuração para que o Spring MVC possa gerenciar no método anotado.
 * - @ComponentScan recebe um array de classes de onde o Spring MVC pode extrair de forma automatica os outros controllers.
 * - @EnableWebMvc anotação para habilitar o recurso Web MVC do Spring MVC.
 */

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoDAO.class}) // Somente com uma classe o Spring reconhece as demais que estão no mesmo pacote
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/views/"); // Caminho das views
	    resolver.setSuffix(".jsp"); // Adiciona extensão dos arquivos de view
	    return resolver;
	}
	
}
