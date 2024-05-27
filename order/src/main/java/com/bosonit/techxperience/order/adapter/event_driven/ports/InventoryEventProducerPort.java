package com.bosonit.techxperience.order.adapter.event_driven.ports;

public interface InventoryEventProducerPort {
    void emitInventory();

    void reserveItems(Integer amount);
}
