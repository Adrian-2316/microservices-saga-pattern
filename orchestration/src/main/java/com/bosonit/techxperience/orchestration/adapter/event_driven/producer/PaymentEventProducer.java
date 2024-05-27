package com.bosonit.techxperience.orchestration.adapter.event_driven.producer;

import com.bosonit.techxperience.orchestration.adapter.event_driven.ports.PaymentEventProducerPort;
import com.bosonit.techxperience.orchestration.domain.Saga;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class PaymentEventProducer implements PaymentEventProducerPort {
    @Channel("payment-process")
    Emitter<Saga> processPaymentEmitter;
    @Channel("payment-cancel-process")
    Emitter<Saga> cancelPaymentEmitter;

    @Override
    public void processPayment(Saga saga) {
        log.info("[SAGA %s] STEP %s - Publishing payment process event".formatted(saga.getId(), saga.getStep()));
        processPaymentEmitter.send(saga);
    }

    @Override
    public void cancelPayment(Saga saga) {
        log.info("[SAGA %s] COMPENSATION %s - Publishing payment cancel process event".formatted(saga.getId(), saga.getStep()));
        cancelPaymentEmitter.send(saga);
    }
}
