package com.avondock.core.shared.gateway;

import com.avondock.core.shared.gateway.contracts.Query;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        return CollectionModel.of(toList(result));
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
        return assertResult.test(result) ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    protected <T> List<T> toList(T result) {
        List<T> list = new ArrayList<>();
        list.add(result);
        return list;
    }
}
