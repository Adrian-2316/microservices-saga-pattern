package com.bosonit.techxperience.orchestration.adapter.event_driven.producer;

import com.bosonit.techxperience.item.adapter.event_driven.ports.ItemEventProducerPort;
import com.bosonit.techxperience.orchestration.domain.Saga;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class ItemEventProducer implements ItemEventProducerPort {

    @Channel("inventory-reserve")
    Emitter<Saga> reservationEmitter;
    @Channel("inventory-remove")
    Emitter<Saga> removeEmitter;
    @Channel("inventory-restock")
    Emitter<Saga> restockEmitter;

    @Channel("inventory-unlock")
    Emitter<Saga> unlockEmitter;

    @Override
    public void reserveItems(Saga saga) {
        log.info("[SAGA %s] STEP %s - Publishing reserving items event".formatted(saga.getId(), saga.getStep()));
        reservationEmitter.send(saga);
    }

    @Override
    public void removeItems(Saga saga) {
        log.info("[SAGA %s] STEP %s - Publishing removing items event".formatted(saga.getId(), saga.getStep()));
        removeEmitter.send(saga);
    }

    @Override
    public void unlockItems(Saga saga) {
        log.info("[SAGA %s] COMPENSATION %s - Publishing unlocking items event".formatted(saga.getId(), saga.getStep()));
        unlockEmitter.send(saga);
    }

    @Override
    public void restockItems(Saga saga) {
        log.info("[SAGA %s] COMPENSATION %s - Publishing restocking items event".formatted(saga.getId(), saga.getStep()));
        restockEmitter.send(saga);
    }
}
