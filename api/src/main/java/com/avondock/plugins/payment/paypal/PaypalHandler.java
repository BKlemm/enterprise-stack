package com.avondock.plugins.payment.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PaypalHandler {
    static String clientId = "CLIENT_ID";
    static String secret = "SECRET";

    // Creating a sandbox environment
    private static final PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, secret);

    // Creating a client for the environment
    static PayPalHttpClient client = new PayPalHttpClient(environment);
}
