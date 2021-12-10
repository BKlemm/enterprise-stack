package com.avondock.core.shared.infrastructure.service;

import com.avondock.core.common.http.WebClientAdapter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseService<T> {

    protected final WebClientAdapter client;

    @PersistenceContext
    EntityManager entityManager;

    private String searchTerm;


    public BaseService() {
        this.client = new WebClientAdapter();
    }

    protected List<T> filter(Class<T> clazz, String apql) {
        CriteriaBuilder  cb     = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query  = cb.createQuery(clazz);
        Root<T>          entity = query.from(clazz);

        query.select(entity);
        query.where(cb.like(entity.get(apql).as(String.class), "%"+searchTerm+"%"));

        return entityManager.createQuery(query).getResultList();
    }

    /**
     * customer:lastName=Klemm
     *
     * @param apql
     */
    private String parseFilter(String apql) {
        String[] parts = apql.split("=");
        String criteria = parts[0];
        searchTerm = parts[1];

        return criteria;
    }
}
