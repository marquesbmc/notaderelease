package com.caixa.notaderelease.api.source;


import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {"com.caixa.notaderelease.api.repository.mysql"})
public class MySQLEntityManager {

	@Bean(name = {"dataSource"})
	  public DataSource dataSource() {
	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
	    dataSourceBuilder.url("jdbc:mysql://localhost/notadereleaseapi?createDatabaseIfNotExist=true&useSSL=false");
	    dataSourceBuilder.username("root");
	    dataSourceBuilder.password("root");
	    return dataSourceBuilder.build();
	  }




	  
	  @Primary
	  @Bean(name = {"entityManagerFactory"})
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
	    HashMap<String, Object> properties = new HashMap<>();
	    properties.put("hibernate.hbm2ddl.auto", "update");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    return builder.dataSource(dataSource).properties(properties)
	      .packages(new String[] { "com.caixa.notaderelease.api.model.mysql"
	        }).persistenceUnit("mysql").build();
	  }



	  
	  @Bean(name = {"transactionManager"})
	  public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) { return (PlatformTransactionManager)new JpaTransactionManager(entityManagerFactory); }
	}
