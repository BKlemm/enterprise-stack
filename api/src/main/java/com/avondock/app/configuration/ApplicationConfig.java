package com.avondock.app.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.avondock.app.configuration.properties.BasePackages.ROOT_PACKAGE;

@Configuration
public class ApplicationConfig {


    @Bean
    XStream xStream(){
        XStream xstream = new XStream();
        // clear out existing permissions and set own ones
        xstream.addPermission(NoTypePermission.NONE);
        // allow any type from the same package
        xstream.allowTypesByWildcard(new String[] {
                ROOT_PACKAGE + ".**",
                "org.axonframework.**",
                "java.**",
                "com.thoughtworks.xstream.**"
        });

        return xstream;
    }

    @Bean
    @Qualifier("eventSerializer")
    public Serializer eventSerializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();
    }

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.usingSubscribingEventProcessors();
    }

    @Bean
    public JsonSchemaGenerator jsonSchemaGenerator(ObjectMapper objectMapper) {
        return new JsonSchemaGenerator(objectMapper);
    }

}
