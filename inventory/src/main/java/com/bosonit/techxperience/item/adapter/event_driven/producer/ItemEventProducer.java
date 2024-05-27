package com.bosonit.techxperience.item.adapter.event_driven.producer;

import com.bosonit.techxperience.item.adapter.event_driven.ports.ItemEventProducerPort;
import com.bosonit.techxperience.item.domain.Saga;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class ItemEventProducer implements ItemEventProducerPort {
    @Channel("inventory-reserve-response")
    Emitter<Saga> reserveEmitter;

    @Channel("inventory-remove-response")
    Emitter<Saga> removeEmitter;

    @Override
    public void reserveInventoryResponse(Saga saga) {
        reserveEmitter.send(saga);
    }

    @Override
    public void removeInventoryResponse(Saga saga) {
        removeEmitter.send(saga);
    }
}
