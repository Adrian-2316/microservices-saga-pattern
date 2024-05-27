package com.bosonit.techxperience.payment.adapter.event_driven.producer;

import com.bosonit.techxperience.payment.adapter.event_driven.ports.PaymentEventProducerPort;
import com.bosonit.techxperience.payment.domain.Saga;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class PaymentEventProducer implements PaymentEventProducerPort {
    @Channel("payment-process-response")
    Emitter<Saga> processEmitter;

    @Override
    public void processPaymentResponse(Saga saga) {
        processEmitter.send(saga);
    }
}
