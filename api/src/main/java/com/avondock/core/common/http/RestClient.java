package com.avondock.core.common.http;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class RestClient {

    private final RestTemplate client;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestClient(RestTemplateBuilder builder, ObjectMapper mapper) {
        client = builder.build();
        objectMapper = mapper;
    }

    public <T> T get(Class<T> clazz, String url, Object... uriParams) {
        try {
            ResponseEntity<String> response = client.getForEntity(url, String.class, uriParams);
            JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
            return read(response, javaType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("No data available {}", url);
            } else {
                log.info("Rest Client Exception {}", exception.getMessage());
            }
        }

        return null;
    }

    public <T> T list(Class<T> clazz, String url, Object... uriParams) {
        try {
            ResponseEntity<String> response = client.getForEntity(url, String.class, uriParams);
            CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return read(response, type);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("No data available {}", url);
            } else {
                log.info("Rest Client Exception {}", exception.getMessage());
            }
        }

        return null;
    }

    public <T, R> T post(Class<T> clazz, String url, R body, Object... uriParams) {
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<String> response = client.postForEntity(url, request, String.class, uriParams);
        JavaType type = objectMapper.getTypeFactory().constructType(clazz);
        return read(response, type);
    }

    public <T, R> T put(Class<T> clazz, String url, R body, Object... uriParams) {
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<String> response = client.exchange(url, HttpMethod.PUT, request, String.class, uriParams);
        JavaType type = objectMapper.getTypeFactory().constructType(clazz);
        return read(response, type);
    }

    public void delete(String url, Object... uriParams) {
        try {
            client.delete(url, uriParams);
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
    }

    private <T> T read(ResponseEntity<String> response, JavaType javaType) {
        T result = null;

        HttpStatus status = response.getStatusCode();

        if (status != HttpStatus.OK || status != HttpStatus.CREATED) {
            log.info("No data available {}", status);
        }

        try {
            result = objectMapper.readValue(response.getBody(), javaType);
        } catch (IOException exception) {
            log.info(exception.getMessage());
        }

        return result;
    }


}
