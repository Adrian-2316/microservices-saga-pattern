package com.bosonit.techxperience.orchestration.adapter.event_driven.consumer;

import com.bosonit.techxperience.orchestration.application.ports.OrchestrationPort;
import com.bosonit.techxperience.orchestration.domain.Saga;
import com.bosonit.techxperience.orchestration.domain.SagaBase;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class PaymentEventConsumer {
    @Inject
    OrchestrationPort orchestrationPort;

    @Incoming("payment-process-response")
    @NonBlocking
    public void processPaymentResponse(SagaBase sagaBase) {
        Saga saga = new Saga(sagaBase);
        orchestrationPort.fillSagaSteps(saga);
        orchestrationPort.processPaymentResponse(saga);
    }
}
