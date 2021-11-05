package com.avondock.app.configuration;

import com.avondock.app.configuration.properties.CustomHibernateJpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static com.avondock.app.configuration.properties.BasePackages.*;

@Configuration
@PropertySource({"classpath:persistence-multiple-db-boot.properties"})
@EnableJpaRepositories(basePackages = {SERVICE_PACKAGE, AXON_SAGA_JPA_REPO}, entityManagerFactoryRef = "mainEntityManager", transactionManagerRef = "mainTransactionManager")
public class PersistenceAvondockAutoConfig {

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
        return DataSourceBuilder
                .create()
                .url(System.getenv().get("SPRING_DATASOURCE_1"))
                .username("root")
                .password("")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }
}
