package com.bosonit.techxperience.payment.adapter.event_driven.consumer;

import com.bosonit.techxperience.payment.adapter.event_driven.ports.PaymentEventProducerPort;
import com.bosonit.techxperience.payment.application.ports.PaymentSagaPort;
import com.bosonit.techxperience.payment.domain.Saga;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class PaymentEventConsumer {
    @Inject
    PaymentSagaPort paymentPort;

    @Inject
    PaymentEventProducerPort paymentEventProducerPort;

    @Incoming("payment-process")
    @NonBlocking
    public void processPayment(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing payment process event".formatted(saga.getId(), saga.getStep()));
        try {
            paymentPort.processPayment();
        } catch (Exception e) {
            saga.setCompensating(true);
        }
        paymentEventProducerPort.processPaymentResponse(saga);
    }

    @Incoming("payment-cancel-process")
    @NonBlocking
    public void cancelPayment(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing payment cancel process event".formatted(saga.getId(), saga.getStep()));
        try {
            paymentPort.cancelPayment();
        } catch (Exception e) {
            saga.setCompensating(true);
        }
        //paymentEventProducerPort.cancelPaymentResponse(saga);

    }
}
