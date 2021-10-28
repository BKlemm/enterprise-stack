package com.avondock.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.avondock.app.configuration.properties.BasePackages.*;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, proxyBeanMethods = false)
@ComponentScan(basePackages = {APP_PACKAGE, CORE_PACKAGE})
// Needed to separated profiled axon instances get work
@EntityScan(basePackages = {SYSTEM_PACKAGE, SERVICE_PACKAGE})
public class AvondockApplication {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AvondockApplication.class);
        application.run(args);
    }
}
