package com.bosonit.techxperience.order.adapter.rest.controller;

import com.bosonit.techxperience.order.adapter.event_driven.ports.InventoryEventProducerPort;
import com.bosonit.techxperience.order.adapter.event_driven.ports.PaymentEventProducerPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("orders/events")
@ApplicationScoped
public class OrderEventController {

    @Inject
    PaymentEventProducerPort paymentProducerPort;

    @Inject
    InventoryEventProducerPort inventoryProducerPort;

    @POST
    @Path("payment")
    public void emitPayment() {
        paymentProducerPort.emitPayment();
    }

    @POST
    @Path("inventory")
    public void emitInventory() {
        inventoryProducerPort.emitInventory();
    }
}
