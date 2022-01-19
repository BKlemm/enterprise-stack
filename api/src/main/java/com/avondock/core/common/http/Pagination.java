package com.avondock.core.common.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pagination {

    protected static ObjectMapper mapper = new ObjectMapper();
    protected static HttpFilter filter;

    public static Pageable of(String filterParams) {
        filter = map(filterParams, HttpFilter.class);
        Sort sort = filter.getSortDirection().equals("asc") ? Sort.by(filter.getActiveSort()).ascending() : Sort.by(filter.getActiveSort()).descending();
        return PageRequest.of(filter.getPageIndex(), filter.getPageSize(), sort);
    }

    public static Pageable of(HttpFilter filter) {
        Sort sort = filter.getSortDirection().equals("asc") ? Sort.by(filter.getActiveSort()).ascending() : Sort.by(filter.getActiveSort()).descending();
        return PageRequest.of(filter.getPageIndex(), filter.getPageSize(), sort);
    }

    public static HttpFilter getFilter() {
        assert !filter.hasActiveSort();
        return filter;
    }

    protected static <T> HttpFilter map(String content, Class<T> valueType) {
        try {
            return (HttpFilter) mapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HttpFilter();
    }

}
