package com.avondock.core.common.http;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
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
        return this.request(path, data, "GET");
    }

    public void post(String path, Object data) {
        this.request(path, data, "POST");
    }

    public void put(String path, Object data) {
        this.request(path, data, "PUT");
    }

    public void delete(String path) throws IOException {
        Request req = new Request("DELETE", path);
        client.performRequest(req);
    }

    public Response request(String path, Object data, String method) {
        Request req = new Request(method, path);

        if (data != null) {
            req.setJsonEntity(new Gson().toJson(data));
        }

        try {
            Response res = client.performRequest(req);
            System.out.println(EntityUtils.toString(res.getEntity()));
            return res;
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
