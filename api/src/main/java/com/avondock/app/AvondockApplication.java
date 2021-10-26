package com.avondock.app;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static com.avondock.app.configuration.properties.BasePackages.*;

@EnableAdminServer
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, proxyBeanMethods = false)
@ComponentScan(basePackages = {APP_PACKAGE, CORE_PACKAGE})
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
