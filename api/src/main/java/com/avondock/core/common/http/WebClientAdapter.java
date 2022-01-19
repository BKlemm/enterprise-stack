package com.avondock.core.common.http;

import com.avondock.core.common.http.contracts.AbstractWebClient;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

@Slf4j
@Component
public class WebClientAdapter {

    @Value("${server.address}")
    String address;

    @Value("${server.port}")
    Integer port;

    @Value("${avondock.endpoint.protocol}")
    String PROTOCOL;

    @Value("${avondock.endpoint.main}")
    String APP;

    String VERSION;

    private final WebClient client;

    public static WebClientAdapter.Builder builder() {
        return new WebClientAdapter.Builder();
    }

    public WebClientAdapter() {
        this.client = WebClient.create(buildUrl());
    }

    public JsonNode get(String resource) {
        return this.client.get().uri(resource).retrieve().bodyToMono(JsonNode.class).block();
    }

    public JsonNode getWith(String resource, Map<String, String> params) {
        UriComponentsBuilder builder = uriBuilder(resource);

        params.forEach(builder::queryParam);

        URI uri = builder.build().toUri();
        log.debug("URL: {}",uri);
        return this.client.get().uri(uri).retrieve().bodyToMono(JsonNode.class).timeout(Duration.ofMillis(10_000)).block();
    }

    public <T> void post(String resource, Object data, Class<?> clazz) throws ClassNotFoundException {
        UriComponentsBuilder builder = uriBuilder(resource);

        URI uri = builder.build().toUri();
        this.client.post().uri(uri).body(Mono.just(data), clazz).retrieve().bodyToMono(Void.class);
    }

    public UriComponentsBuilder uriBuilder(String resource) {
        assert VERSION != null;
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.host(address).port(port).scheme(PROTOCOL);
        builder.path(APP+"/"+VERSION+"/"+resource);

        return builder;
    }

    public String buildUrl() {
        assert VERSION != null;
        return PROTOCOL + "://" + address + ":" + port + "/" + APP + "/" + VERSION + "/";
    }

    public static class Builder extends AbstractWebClient.Builder {
        public Builder() {}
        public WebClientAdapter.Builder client(WebClientAdapter client) {
            super.client(client);
            return this;
        }
    }
}
