package com.bosonit.techxperience.payment.adapter.event_driven.ports;

import com.bosonit.techxperience.payment.domain.Saga;

public interface PaymentEventProducerPort {

    void processPaymentResponse(Saga saga);
}
