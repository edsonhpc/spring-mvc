package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Edson Cavalcanti
 * - O objetivo dessa classe é fornecer o acesso ao banco de dados, para isso passamos todas as configurações necessárias abaixo.
 * - O método entityManagerFactoryBean é gerenciado pelo Spring, é ele que vai criar o EntityManager usado em nosso DAO, ele também possui as configurações do banco.
 * - Ao criar o método acima para gerar  EntityManager, este precisa de um adpter e estamos passando um que o próprio Hibernate disponibiliza.
 * - Depois de tudo configurado precisamos disponiblizar essa configuração para o Spring, e isso é feito na classe ServletSpringMVC no método getServletConfigClass
 * - O método transactionManager é criado para gerenciar as transações e precisamos que ele reconheça o no EntityManager ou seja o gerenciador de entidades. 
 * - @EnableTransactionManagement é ativa o gerenciamento de transaões e já reconhece o TransctionManager.
 * - Depois devemos ir nas classes DAO e e realizar a anotação @Transactional.
 */

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); // Adpter disponibilizado pelo Hibernate
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("masterkey");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		factoryBean.setDataSource(dataSource);
		
		Properties props = new Properties(); // Propriedades adicionais do banco de dados.
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(props);

		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models"); // Configuração para o EntityManager deve encontrar os Models
		
		return factoryBean; 
	}
	
	@Bean
	public JpaTransactionManager transactionManager (EntityManagerFactory emf) {
		return new JpaTransactionManager();
	}
	
}
