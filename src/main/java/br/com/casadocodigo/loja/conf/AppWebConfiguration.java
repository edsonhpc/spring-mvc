package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;

/**
 * 
 * @author Edson Cavalcanti
 * - Essa classe tem a finalidade de informar ao Spring o diretório onde estaram as views do projeto.
 * - Objetivo de criar a pasta WEB-INF é de proteger os arquivos para não serem acessados diretamente sem passar pelo controller.
 * - InternalResourceViewResolver = Resolvedor Interno de Recursos View que ajuda o Spring a encontrar as views.
 * - @Bean é uma anotação de configuração para que o Spring MVC possa gerenciar o método anotado.
 * - @ComponentScan recebe um array de classes de onde o Spring MVC pode extrair de forma automatica os outros controllers.
 * - @EnableWebMvc anotação para habilitar o recurso Web MVC do Spring MVC.
 * - MessageSource esse método é usado para o carregamento dos arquivos de mensagens.
 * 
 * ---- Explicação do método mvcConversionService / usado para formatar o padrão de data
 * - DefaultFormattingConversionService é responsável pelo serviço de conversão de formato.
 * - DateFormatterRegistrar este fará o registro de data usado para conversão, este objeto recebe DateFormatter.
 * - DateFormatter este guardará efetivamente o padrão da data que é dd/MM/yyyy
 * - Por último usamos o registrador para registrar o padrão de data no serviço de conversão.
 * 
 * --- multipartResolver
 *  - Esse método se refere a um resolvedor de dados multimídia, quanto temos texto e arquivos por exemplo.
 *  - Os arquivos podem ser: imagem, PDF e outros, esse objeto identifica cada um dos recursos enviados e nos fornece uma forma simples de manipulá-los.
 */

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, 
									 ProdutoDAO.class,
									 FileSaver.class}) // Somente com uma classe o Spring reconhece as demais que estão no mesmo pacote
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/views/"); // Caminho das views
	    resolver.setSuffix(".jsp"); // Adiciona extensão dos arquivos de view
	    return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1); // É o tempo que o Spring irá recarregar o arquivo de temos em tempos reload automático
		return messageSource;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver() { // Configuração do Spring para se trabalhar com arquivos
		return new StandardServletMultipartResolver();
	}
	
	
}
