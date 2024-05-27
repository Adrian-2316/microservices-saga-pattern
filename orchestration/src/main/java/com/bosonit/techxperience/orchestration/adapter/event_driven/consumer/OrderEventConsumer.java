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
public class OrderEventConsumer {
    @Inject
    OrchestrationPort orchestrationPort;

    @Incoming("order-place-response")
    @NonBlocking
    public void placeOrderResponse(SagaBase sagaBase) {
        Saga saga = new Saga(sagaBase);
        orchestrationPort.fillSagaSteps(saga);
        orchestrationPort.placeOrderResponse(saga);
    }


    @Incoming("order-ship-response")
    @NonBlocking
    public void shipOrderResponse(SagaBase sagaBase) {
        Saga saga = new Saga(sagaBase);
        orchestrationPort.fillSagaSteps(saga);
        orchestrationPort.shipOrderResponse(saga);
    }
}
