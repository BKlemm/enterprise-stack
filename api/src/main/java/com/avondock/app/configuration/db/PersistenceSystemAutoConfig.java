package com.avondock.app.configuration.db;

import com.avondock.app.configuration.properties.CustomHibernateJpaProperties;
import com.avondock.core.common.annotation.ConditionProfileIsMain;
import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.sql.common.SqlStorageProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.util.Map;

import static com.avondock.app.configuration.properties.BasePackages.SYSTEM_PACKAGE;

@Configuration
@PropertySource({"classpath:persistence-multiple-db-boot.properties"})
@EnableJpaRepositories(basePackages = {SYSTEM_PACKAGE}, entityManagerFactoryRef = "systemEntityManager", transactionManagerRef = "systemTransactionManager")
public class PersistenceSystemAutoConfig {

    public PersistenceSystemAutoConfig() {
        super();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource systemDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean systemEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new CustomHibernateJpaProperties().entityManager();
        if (System.getenv().get("SPRING_DATASOURCE_2") == null) {
            em.setDataSource(systemDataSource());
        } else {
            em.setDataSource(customDataSource());
        }
        em.setPackagesToScan(SYSTEM_PACKAGE);

        return em;
    }

    @Bean
    public PlatformTransactionManager systemTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(systemEntityManager().getObject());
        return transactionManager;
    }

    public DataSource customDataSource() {
        return DataSourceBuilder
                .create()
                .url(System.getenv().get("SPRING_DATASOURCE_2"))
                .username("root")
                .password("")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    @Bean
    public JobScheduler configureJobrunr(ApplicationContext context) {
        return JobRunr.configure()
                .useJobActivator(context::getBean)
                .useStorageProvider(SqlStorageProviderFactory.using(systemDataSource()))
                .useBackgroundJobServer()
                .useDashboard()
                .initialize();
    }
}
