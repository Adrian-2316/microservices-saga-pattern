package com.bosonit.techxperience.payment.adapter.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "order-service")
public interface OrderClient {
}
