package com.bosonit.techxperience.order.adapter.event_driven.producer;

import com.bosonit.techxperience.order.adapter.event_driven.ports.InventoryEventProducerPort;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class InventoryEventProducer implements InventoryEventProducerPort {
    @Channel("inventories")
    Emitter<String> emitter;

    @Channel("reserve-items")
    Emitter<Integer> reserveItemsEmitter;

    @Override
    public void emitInventory() {
        emitter.send("Inventory event emitted");
    }

    @Override
    public void reserveItems(Integer amount) {
        reserveItemsEmitter.send(amount);
    }
}
