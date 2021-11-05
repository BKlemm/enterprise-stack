package com.avondock.core.common.mail;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value={"classpath:application-mail.properties"})
public class MailProperties {

    @Value("${spring.mail.username}")
    String account;

    @Value("${spring.mail.password}")
    String password;

    @Value("${spring.mail.host}")
    String host;


}
