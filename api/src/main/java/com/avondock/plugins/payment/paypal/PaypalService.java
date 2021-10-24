package com.avondock.plugins.payment.paypal;

import com.avondock.core.plugin.payment.OrderResponse;
import com.avondock.core.plugin.payment.PaymentService;
import com.paypal.http.exceptions.HttpException;
import com.paypal.orders.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService implements PaymentService {

    public OrderResponse createOrder(String amount)  {
        OrderRequest request = new OrderRequest();
        request.checkoutPaymentIntent("CAPTURE");

        List<PurchaseUnitRequest> units = new ArrayList<>();
        units.add(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode("EUR").value(amount)));
        request.purchaseUnits(units);

        OrdersCreateRequest req = new OrdersCreateRequest().requestBody(request);

        try {
            return new PaypalResponse(PaypalHandler.client.execute(req));
        } catch (IOException e) {
            if (e instanceof HttpException) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    public OrderResponse capture(String orderId) {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);

        try {
            return new PaypalResponse(PaypalHandler.client.execute(request));
        } catch (IOException e) {
            if (e instanceof HttpException) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public OrderResponse refund(String captureId) {
        return null;
    }
}
