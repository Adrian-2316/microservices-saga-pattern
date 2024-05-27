package com.bosonit.techxperience.orchestration.application.service;

import com.bosonit.techxperience.item.adapter.event_driven.ports.ItemEventProducerPort;
import com.bosonit.techxperience.orchestration.adapter.event_driven.ports.OrderEventProducerPort;
import com.bosonit.techxperience.orchestration.adapter.event_driven.ports.PaymentEventProducerPort;
import com.bosonit.techxperience.orchestration.application.ports.OrchestrationPort;
import com.bosonit.techxperience.orchestration.domain.Saga;
import com.bosonit.techxperience.orchestration.domain.Step;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class OrchestrationService implements OrchestrationPort {

    @Inject
    OrderEventProducerPort orderEventProducerPort;

    @Inject
    PaymentEventProducerPort paymentEventProducerPort;

    @Inject
    ItemEventProducerPort itemEventProducerPort;

    @Override
    public Uni<Void> executeSaga(int failurePercentage) {
        Saga saga = new Saga();
        saga.setFailurePercentage(failurePercentage);
        fillSagaSteps(saga);
        saga.nextStep();
        return Uni.createFrom().nullItem();
    }

    @Override
    public void fillSagaSteps(Saga saga) {
        Step step = new Step();
        step.setOperation(c -> orderEventProducerPort.placeOrder(saga));
        step.setCompensation(c -> orderEventProducerPort.cancelOrder(saga));
        saga.addStep(step);
        step = new Step();
        step.setOperation(c -> itemEventProducerPort.reserveItems(saga));
        step.setCompensation(c -> itemEventProducerPort.removeItems(saga));
        saga.addStep(step);
        step = new Step();
        step.setOperation(c -> paymentEventProducerPort.processPayment(saga));
        step.setCompensation(c -> paymentEventProducerPort.cancelPayment(saga));
        saga.addStep(step);
        step = new Step();
        step.setOperation(c -> orderEventProducerPort.shipOrder(saga));
        step.setCompensation(c -> orderEventProducerPort.cancelShipOrder(saga));
        saga.addStep(step);
        step = new Step();
        step.setOperation(c -> itemEventProducerPort.removeItems(saga));
        step.setCompensation(c -> itemEventProducerPort.restockItems(saga));
        saga.addStep(step);
    }

    @Override
    public void placeOrderResponse(Saga saga) {
        if (saga.isCompensating()) saga.compensate();
        else saga.nextStep();
    }

    @Override
    public void reserveInventoryResponse(Saga saga) {
        if (saga.isCompensating()) saga.compensate();
        else saga.nextStep();
    }

    @Override
    public void processPaymentResponse(Saga saga) {
        if (saga.isCompensating()) saga.compensate();
        else saga.nextStep();
    }

    @Override
    public void shipOrderResponse(Saga saga) {
        if (saga.isCompensating()) saga.compensate();
        else saga.nextStep();
    }

    @Override
    public void removeInventoryResponse(Saga saga) {
        if (saga.isCompensating()) saga.compensate();
        else saga.nextStep();
    }
}
