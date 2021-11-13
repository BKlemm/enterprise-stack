package com.avondock.app.configuration.axon;

import com.avondock.app.service.carpark.cqrs.command.factory.CarParkCommandFactory;
import com.avondock.app.service.carpark.infrastucture.factory.CarParkCommandFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

    @Bean
    public CarParkCommandFactory carParkCommandFactory() {
        return new CarParkCommandFactoryImpl();
    }
}
