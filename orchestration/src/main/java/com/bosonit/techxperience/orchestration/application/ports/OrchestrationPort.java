package com.bosonit.techxperience.orchestration.application.ports;


import com.bosonit.techxperience.orchestration.domain.Saga;
import io.smallrye.mutiny.Uni;

public interface OrchestrationPort {
    Uni<Void> executeSaga(int failurePercentage);

    void fillSagaSteps(Saga saga);

    void placeOrderResponse(Saga saga);

    void reserveInventoryResponse(Saga saga);

    void processPaymentResponse(Saga saga);

    void shipOrderResponse(Saga saga);

    void removeInventoryResponse(Saga saga);
}
