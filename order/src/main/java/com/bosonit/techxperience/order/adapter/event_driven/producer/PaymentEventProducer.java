package com.bosonit.techxperience.order.adapter.event_driven.producer;

import com.bosonit.techxperience.order.adapter.event_driven.ports.PaymentEventProducerPort;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class PaymentEventProducer implements PaymentEventProducerPort {
    @Channel("payments")
    Emitter<String> emitter;

    @Override
    public void emitPayment() {
        emitter.send("Payment event emitted");
    }
}
