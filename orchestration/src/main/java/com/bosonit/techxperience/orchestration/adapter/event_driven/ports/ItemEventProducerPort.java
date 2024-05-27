package com.bosonit.techxperience.item.adapter.event_driven.ports;

import com.bosonit.techxperience.orchestration.domain.Saga;

public interface ItemEventProducerPort {
    void reserveItems(Saga saga);

    void removeItems(Saga saga);

    void unlockItems(Saga saga);

    void restockItems(Saga saga);
}
