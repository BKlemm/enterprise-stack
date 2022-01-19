package com.avondock.core.common.http;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class HttpFilter {

    public static String FILTER_ALL = "all";

    String filter = FILTER_ALL;
    String sortDirection = "asc";
    String activeSort = "";
    Integer pageIndex = 0;
    Integer pageSize = 20;

    public boolean hasActiveSort() {
        return !Objects.equals(this.activeSort, "") && this.activeSort != null;
    }

    public boolean isFilterable() {
        return filter != null && !filter.equals(FILTER_ALL) && !filter.equals("");
    }
}
