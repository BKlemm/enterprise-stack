package com.avondock.plugins.payment.paypal;

import com.avondock.core.plugin.payment.OrderResponse;
import com.paypal.http.Headers;
import com.paypal.http.HttpResponse;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Order;

import java.util.HashMap;
import java.util.Map;

public class PaypalResponse implements OrderResponse {

    private final Order order;
    private final int statusCode;
    private final Headers headers;

    public PaypalResponse(HttpResponse<Order> order) {
        this.order = order.result();
        this.statusCode = order.statusCode();
        this.headers = order.headers();
    }

    public String id() {
        return this.order.id();
    }

    public Map<String, String> links() {
        Map<String, String> links = new HashMap<>();
        for (LinkDescription link: this.order.links()) {
            links.put(link.rel(), link.href());
        }
        return links;
    }
}
