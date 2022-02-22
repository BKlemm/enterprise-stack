package com.avondock.core.common.util.search;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {

    private List<SearchCriteria> params = new ArrayList<>();

    public SpecificationBuilder<T> with(String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<T> result = new GenericSpecification<T>(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            //TODO: implement or-predicate and execute here as ternary op
            result = Specification.where(result).and(new GenericSpecification<T>(params.get(i)));
        }

        return result;
    }
}
