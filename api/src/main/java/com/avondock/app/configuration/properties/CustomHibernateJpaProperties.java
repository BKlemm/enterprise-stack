package com.avondock.app.configuration.properties;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

public class CustomHibernateJpaProperties {

    public LocalContainerEntityManagerFactoryBean entityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(additionalProperties());

        return em;
    }

    public Map<String, Object> additionalProperties() {
        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2dll.create_namespaces", true);
        properties.put("hibernate.dialect","org.hibernate.dialect.MariaDB103Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.implicit_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        properties.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");

        return properties;
    }
}
