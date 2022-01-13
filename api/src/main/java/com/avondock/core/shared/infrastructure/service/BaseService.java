package com.avondock.core.shared.infrastructure.service;

import com.avondock.core.common.http.WebClientAdapter;
import com.avondock.core.common.http.ElasticRestClient;
import com.avondock.core.shared.domain.contracts.Entity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseService<T extends Entity, ID, R extends CrudRepository<T, ID>> {

    protected final WebClientAdapter client;
    protected final ElasticRestClient elastic;

    @PersistenceContext
    EntityManager entityManager;

    protected R repository;

    private String searchTerm;

    protected static final String FILTER_ALL = "all";


    public BaseService(R repository) {
        this.client = new WebClientAdapter();
        this.repository = repository;
        this.elastic = new ElasticRestClient();
    }

    protected List<T> filter(Class<T> clazz, String apql) {
        CriteriaBuilder  cb     = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query  = cb.createQuery(clazz);
        Root<T>          entity = query.from(clazz);

        query.select(entity);
        query.where(cb.like(entity.get(parseFilter(apql)).as(String.class), "%"+searchTerm+"%"));

        return entityManager.createQuery(query).getResultList();
    }

    /**
     * customer.lastName=Klemm
     *
     * @param apql
     */
    private String parseFilter(String apql) {
        String[] parts = apql.split("=");
        String criteria = parts[0];
        searchTerm = parts[1];

        return criteria;
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
