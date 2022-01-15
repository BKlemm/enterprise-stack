package com.avondock.core.common.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpFilter {
    String filter = "all";
    String sortDirection = "asc";
    String activeSort = "";
    Integer pageIndex = 0;
    Integer pageSize = 20;
}
