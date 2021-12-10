package com.avondock.core.common.http;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ElasticRestClient {

    private final RestClient client;

    public ElasticRestClient() {
        client = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http")
        ).build();
    }

    public Response get(String path, Object data) throws IOException {
        Request req = new Request("GET", path);

        if (data != null) {
            req.setJsonEntity(new Gson().toJson(data));
        }

        return client.performRequest(req);
    }

    public void post(String path, Object data) throws IOException {
        Request req = new Request("POST", path);

        if (data != null) {
            req.setJsonEntity(new Gson().toJson(data));
        }

        client.performRequest(req);
    }

    public void delete(String path) throws IOException {
        Request req = new Request("DELETE", path);
        client.performRequest(req);
    }
}
