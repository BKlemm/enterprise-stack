package com.avondock.core.common.util.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private SearchOperation operation;
    private Object value;
}
