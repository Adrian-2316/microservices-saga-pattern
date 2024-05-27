package com.bosonit.techxperience.order.adapter.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "payment-service")
public interface PaymentClient {
}
