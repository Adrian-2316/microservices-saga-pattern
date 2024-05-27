package com.bosonit.techxperience.orchestration.adapter.event_driven.ports;

import com.bosonit.techxperience.orchestration.domain.Saga;

public interface PaymentEventProducerPort {
    void processPayment(Saga saga);

    void cancelPayment(Saga saga);
}
