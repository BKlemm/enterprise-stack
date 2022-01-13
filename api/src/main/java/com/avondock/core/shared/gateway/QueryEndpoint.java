package com.avondock.core.shared.gateway;

import com.avondock.core.common.util.UUIDConverter;
import com.avondock.core.shared.gateway.contracts.Query;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

public class QueryEndpoint {

    protected final QueryGateway   queryGateway;

    public QueryEndpoint(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    protected <T> CompletableFuture<CollectionModel<List<T>>> list(Query query, Class<T> readModel) {
        return queryGateway
                .query(query, ResponseTypes.multipleInstancesOf(readModel))
                .thenApply(this::wrapResultList);
    }

    @NotNull
    protected <T> CollectionModel<T> wrapResultList(T result) {
        return CollectionModel.of(List.of(result));
    }

    protected <T> CompletableFuture<ResponseEntity<T>> get(Query query, Class<T> readModel) throws ExecutionException, InterruptedException {
        return queryGateway.query(query, ResponseTypes.instanceOf(readModel)).thenApply(this::wrapResult);
    }

    @NotNull
    protected <T> ResponseEntity<T> wrapResult(T result) {
        return wrapResult(Objects::isNull, result);
    }

    @NotNull
    protected <T> ResponseEntity<T> wrapResult(Predicate<T> assertResult, T result) {
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Request-Id", UUIDConverter.toBase64());
        return assertResult.test(result) ? ResponseEntity.notFound().build() : new ResponseEntity<>(
                result, header, HttpStatus.OK
        );
    }
}
