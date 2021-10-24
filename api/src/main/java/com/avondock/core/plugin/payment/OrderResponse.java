package com.avondock.core.plugin.payment;

import java.util.Map;

public interface OrderResponse {
    String id();
    Map<String, String> links();
}
