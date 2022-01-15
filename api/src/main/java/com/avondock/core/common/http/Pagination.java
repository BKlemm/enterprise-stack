package com.avondock.core.common.http;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pagination {

    public static Pageable of(HttpFilter filter) {
        Sort sort = filter.getSortDirection().equals("asc") ? Sort.by(filter.getActiveSort()).ascending() : Sort.by(filter.getActiveSort()).descending();
        return PageRequest.of(filter.getPageIndex(), filter.getPageSize(), sort);
    }
}
