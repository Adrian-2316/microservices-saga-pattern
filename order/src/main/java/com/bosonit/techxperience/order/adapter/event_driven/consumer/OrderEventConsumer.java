package com.bosonit.techxperience.order.adapter.event_driven.consumer;

import com.bosonit.techxperience.order.adapter.event_driven.ports.OrderEventProducerPort;
import com.bosonit.techxperience.order.application.ports.OrderPort;
import com.bosonit.techxperience.order.application.ports.OrderSagaPort;
import com.bosonit.techxperience.order.domain.Saga;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class OrderEventConsumer {
    @Inject
    OrderSagaPort orderPort;
    @Inject
    OrderEventProducerPort orderEventProducerPort;

    @Incoming("order-place")
    @NonBlocking
    public void placeOrder(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing placing order event".formatted(saga.getId(), saga.getStep()));
        try {
            Thread.sleep(1000);
            orderPort.placeOrder();
        } catch (Exception e) {
            saga.setCompensating(true);
        }
        orderEventProducerPort.placeOrderResponse(saga);
    }

    @Incoming("order-ship")
    @NonBlocking
    public void shipOrder(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing shipping order event".formatted(saga.getId(), saga.getStep()));
        try {
            Thread.sleep(1000);
            orderPort.shipOrder();
        } catch (Exception e) {
            saga.setCompensating(true);
        }
        orderEventProducerPort.shipOrderResponse(saga);
    }

    @Incoming("order-cancel")
    @NonBlocking
    public void cancelOrder(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing cancel order event".formatted(saga.getId(), saga.getStep()));
        orderPort.cancelOrder();
    }

    @Incoming("order-cancel-ship")
    @NonBlocking
    public void cancelShipOrder(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing cancel ship order event".formatted(saga.getId(), saga.getStep()));
        orderPort.cancelShipOrder();
    }
}
