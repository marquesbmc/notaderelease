package com.caixa.notaderelease.api.source;


import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory", transactionManagerRef = "db2TransactionManager", basePackages = {"com.caixa.notaderelease.api.repository.db2"})
public class DB2EntityManager {
	
	
	 @Bean(name = {"db2DataSource"})
	  public DataSource dataSource() {
	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName("com.ibm.db2.jcc.DB2Driver");
	    dataSourceBuilder.url("jdbc:db2://10.216.80.111:446/RJKDB2DSDH:retrieveMessagesFromServerOnGetMessage=true;emulateParameterMetaDataForZCalls=1;");
	    dataSourceBuilder.username("sfugtr02");
	    dataSourceBuilder.password("qwerxyzh");
	    return dataSourceBuilder.build();
	  }
	 
	 @Bean(name = {"db2EntityManagerFactory"})
	  public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("db2DataSource") DataSource dataSource) {
	    HashMap<String, Object> properties = new HashMap<>();
	    
	    properties.put("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
	    
	    return builder.dataSource(dataSource).properties(properties)
	      .packages(new String[] { "com.caixa.notaderelease.api.model.db2" }).persistenceUnit("db2").build();
	  }


	  
	  @Bean(name = {"db2TransactionManager"})
	  public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory") EntityManagerFactory db2EntityManagerFactory) { return (PlatformTransactionManager)new JpaTransactionManager(db2EntityManagerFactory); }
	}

