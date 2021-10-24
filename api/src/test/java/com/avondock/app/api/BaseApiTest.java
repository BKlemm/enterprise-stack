package com.avondock.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseApiTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    String HOST = "localhost";
    String PROTOCOL = "http";
    String APP = "api";
    String VERSION = "v1";

    public String url(String resource, Map<String, String> params) {
        UriComponentsBuilder builder = uriBuilder(resource);

        params.forEach(builder::queryParam);

        return builder.build().toUri().toString();
    }

    public String url(String resource) {
        UriComponentsBuilder builder = uriBuilder(resource);
        return builder.build().toUri().toString();
    }

    public UriComponentsBuilder uriBuilder(String resource) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.host(HOST).port(port).scheme(PROTOCOL);
        builder.path(APP+"/"+VERSION+"/"+resource);

        return builder;
    }

    public <T> ResponseEntity<T> response(String resource, Class<T> clazz) {
        return this.restTemplate.getForEntity(url(resource), clazz);
    }

    public <T> ResponseEntity<T> response(String resource, Class<T> clazz, Map<String, String> params) {
        return this.restTemplate.getForEntity(url(resource, params), clazz);
    }
}
