package com.avondock.app.configuration;


import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;

import static com.avondock.app.configuration.properties.BasePackages.*;

//@Configuration
@EnableJpaRepositories(basePackages = {SERVICE_PACKAGE}, entityManagerFactoryRef = "cassandraEntityManager", transactionManagerRef = "cassandraTransactionManager")
public class CassandraOgmConfig {


    @Bean
    public LocalContainerEntityManagerFactoryBean cassandraEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setPersistenceProviderClass(HibernateOgmPersistence.class);
        em.setPackagesToScan(SERVICE_PACKAGE);
        em.setPersistenceUnitName("ogm-cass");

        final CassJpaVendorAdpater vendorAdapter = new CassJpaVendorAdpater();
        em.setJpaVendorAdapter(vendorAdapter);

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.transactionType", "resource_local");
        properties.put("hibernate.ogm.datastore.provider", "org.hibernate.ogm.datastore.cassandra.impl.CassandraDatastoreProvider");
        properties.put("hibernate.ogm.datastore.database", "avondock");
        properties.put("hibernate.ogm.datastore.host", "127.0.0.1");
        properties.put("hibernate.implicit_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        properties.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager cassandraTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(cassandraEntityManager().getObject());
        return transactionManager;
    }
}

class CassJpaVendorAdpater extends HibernateJpaVendorAdapter {
    public HibernateOgmPersistence persistenceProvider() {
        return new HibernateOgmPersistence();
    }
}
