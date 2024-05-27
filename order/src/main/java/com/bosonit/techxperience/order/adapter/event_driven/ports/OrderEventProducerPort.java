package com.bosonit.techxperience.order.adapter.event_driven.ports;

import com.bosonit.techxperience.order.domain.Saga;

public interface OrderEventProducerPort {
    void shipOrderResponse(Saga saga);

    void placeOrderResponse(Saga saga);
}
