package com.bosonit.techxperience.orchestration.adapter.event_driven.producer;

import com.bosonit.techxperience.orchestration.adapter.event_driven.ports.OrderEventProducerPort;
import com.bosonit.techxperience.orchestration.domain.Saga;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class OrderEventProducer implements OrderEventProducerPort {
    @Channel("order-place")
    Emitter<Saga> placeOrderEmitter;
    @Channel("order-ship")
    Emitter<Saga> shipOrderEmitter;
    @Channel("order-cancel")
    Emitter<Saga> cancelOrderEmitter;
    @Channel("order-cancel-ship")
    Emitter<Saga> cancelShipOrderEmitter;

    @Override
    public void placeOrder(Saga saga) {
        log.info("[SAGA %s] STEP %s - Publishing placing order event".formatted(saga.getId(), saga.getStep()));
        placeOrderEmitter.send(saga);
    }


    @Override
    public void shipOrder(Saga saga) {
        log.info("[SAGA %s] STEP %s - Publishing shipping order event".formatted(saga.getId(), saga.getStep()));
        shipOrderEmitter.send(saga);
    }

    @Override
    public void cancelOrder(Saga saga) {
        log.info("[SAGA %s] COMPENSATION %s - Publishing cancel order event".formatted(saga.getId(), saga.getStep()));
        cancelOrderEmitter.send(saga);
    }

    @Override
    public void cancelShipOrder(Saga saga) {
        log.info("[SAGA %s] COMPENSATION %s - Publishing cancel ship order event".formatted(saga.getId(), saga.getStep()));
        cancelShipOrderEmitter.send(saga);
    }
}
