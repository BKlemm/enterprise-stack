package com.avondock.core.shared.infrastructure.service;

import com.avondock.core.common.http.WebClientAdapter;
import com.avondock.core.common.http.ElasticRestClient;
import com.avondock.core.common.util.search.SearchOperation;
import com.avondock.core.common.util.search.SpecificationBuilder;
import com.avondock.core.shared.domain.contracts.Entity;
import com.google.common.base.Joiner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseService<T extends Entity, ID, R extends CrudRepository<T, ID> & JpaSpecificationExecutor<T>> {

    protected final WebClientAdapter client;
    protected final ElasticRestClient elastic;

    protected R repository;

    public BaseService(R repository) {
        this.client = new WebClientAdapter();
        this.repository = repository;
        this.elastic = new ElasticRestClient();
    }

    protected List<T> filter(String apql) {
        SpecificationBuilder<T> builder           = new SpecificationBuilder<>();
        String                  operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern                 pattern           = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher                 matcher           = pattern.matcher(apql + ",");
        while (matcher.find()) {
            builder.with(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }

        Specification<T> spec = builder.build();
        return repository.findAll(spec);

    }

    public T save(T  entity) {
        T _entity = repository.save(entity);
        elastic.post(this.getClass().getSimpleName().toLowerCase() + "/_doc/" + entity.getIdentity(), entity);
        return _entity;
    }

    public T update(T  entity) {
        T _entity = repository.save(entity);
        elastic.put(this.getClass().getSimpleName().toLowerCase() + "/_doc/" + entity.getIdentity(), entity);
        return _entity;
    }
}
