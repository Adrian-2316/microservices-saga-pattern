package com.bosonit.techxperience.order.application.service;

import com.bosonit.techxperience.order.adapter.event_driven.ports.InventoryEventProducerPort;
import com.bosonit.techxperience.order.adapter.persistence.ports.OrderRepositoryPort;
import com.bosonit.techxperience.order.application.ports.OrderSagaPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class OrderSagaService implements OrderSagaPort {

    @Inject
    OrderRepositoryPort orderRepositoryPort;
    @Inject
    InventoryEventProducerPort inventoryEventProducerPort;

    @Override
    public void placeOrder() {
    }

    @Override
    public void shipOrder() {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void cancelShipOrder() {

    }
}
