package com.bosonit.techxperience.order.adapter.event_driven.producer;

import com.bosonit.techxperience.order.adapter.event_driven.ports.OrderEventProducerPort;
import com.bosonit.techxperience.order.domain.Saga;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class OrderEventProducer implements OrderEventProducerPort {
    @Channel("order-place-response")
    Emitter<Saga> placeOrderResponseEmitter;

    @Channel("order-ship-response")
    Emitter<Saga> shipOrderResponseEmitter;

    @Override
    public void placeOrderResponse(Saga saga) {
        placeOrderResponseEmitter.send(saga);
    }

    @Override
    public void shipOrderResponse(Saga saga) {
        shipOrderResponseEmitter.send(saga);
    }
}
