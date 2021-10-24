package com.avondock.core.plugin.payment;

public interface PaymentService {

    OrderResponse createOrder(String amount);
    OrderResponse capture(String orderId);
    OrderResponse refund(String captureId);
}
