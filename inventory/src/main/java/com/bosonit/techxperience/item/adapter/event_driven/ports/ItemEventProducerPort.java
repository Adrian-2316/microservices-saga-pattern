package com.bosonit.techxperience.item.adapter.event_driven.ports;

import com.bosonit.techxperience.item.domain.Saga;

public interface ItemEventProducerPort {
    void reserveInventoryResponse(Saga saga);

    void removeInventoryResponse(Saga saga);
}
