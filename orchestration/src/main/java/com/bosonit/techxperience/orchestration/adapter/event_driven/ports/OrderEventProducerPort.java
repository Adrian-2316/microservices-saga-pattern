package com.bosonit.techxperience.orchestration.adapter.event_driven.ports;

import com.bosonit.techxperience.orchestration.domain.Saga;

import java.util.concurrent.Flow;

public interface OrderEventProducerPort {

    void placeOrder(Saga saga);

    void shipOrder(Saga saga);

    void cancelOrder(Saga saga);

    void cancelShipOrder(Saga saga);
}
