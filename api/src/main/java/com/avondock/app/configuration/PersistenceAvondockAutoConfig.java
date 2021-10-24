package com.avondock.app.configuration;

import com.avondock.app.configuration.properties.CustomHibernateJpaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.util.Map;

import static com.avondock.app.configuration.properties.BasePackages.*;

@Configuration
@PropertySource({"classpath:persistence-multiple-db-boot.properties"})
@EnableJpaRepositories(basePackages = {SERVICE_PACKAGE, AXON_SAGA_JPA_REPO}, entityManagerFactoryRef = "mainEntityManager", transactionManagerRef = "mainTransactionManager", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class PersistenceAvondockAutoConfig {

    public PersistenceAvondockAutoConfig() {
        super();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mainEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new CustomHibernateJpaProperties().entityManager();
        em.setPackagesToScan(SERVICE_PACKAGE, AXON_SAGA_JPA_REPO);
        if (System.getenv().get("SPRING_DATASOURCE_1") == null) {
            System.out.println("SPRING_DATASOURCE_1 IS NULL");
            em.setDataSource(mainDataSource());
        } else {
            System.out.println(System.getenv().get("SPRING_DATASOURCE_1"));
            em.setDataSource(customDataSource());
        }
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager mainTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mainEntityManager().getObject());
        return transactionManager;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public DataSource customDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl(System.getenv().get("SPRING_DATASOURCE_1"));
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
        /**
        return DataSourceBuilder
                .create()
                .url(System.getenv().get("SPRING_DATASOURCE_1"))
                .username("root")
                .password("")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();*/
    }
}
