package com.favendo.portal.contextconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@Configuration
@Import({PropertySourceConfiguration.class})
public class DataSourceContextConfig {

    private static final String PERSISTENCE_XML_LOCATION = "classpath*:META-INF/persistence.xml";

    private static final String PERSISTENCE_UNIT_NAME = "storecastPersistenceUnit";

    private static final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "dataSource")
    public DriverManagerDataSource postgredataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        return driverManagerDataSource;
    }

    @Bean(name = "persistenceUnitManager")
    public DefaultPersistenceUnitManager postgrepersistenceUnitManager() {
        DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
        persistenceUnitManager.setPersistenceXmlLocations(PERSISTENCE_XML_LOCATION);
        persistenceUnitManager.setDefaultDataSource(postgredataSource());
        return persistenceUnitManager;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgreEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(postgredataSource());
        entityManagerFactoryBean.setPersistenceUnitManager(postgrepersistenceUnitManager());
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        Properties properties = new Properties();
        properties.put("hibernate.ejb.entitymanager_factory_name", ENTITY_MANAGER_FACTORY);
        entityManagerFactoryBean.setJpaProperties(properties);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgreEntityManagerFactory().getObject());
        return transactionManager;
    }
}
