package com.bosonit.techxperience.orchestration.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class Saga extends SagaBase {
    private final List<Step> steps = new ArrayList<>();

    public Saga(SagaBase sagaBase) {
        super(sagaBase);
    }

    public void nextStep() {
        if (this.getStep() < steps.size()) {
            int i = this.incrementStep();
            steps.get(i).getOperation().accept(this);
        } else log.info("Saga finished successfully");
    }

    public void compensate() {
        if (this.getStep() > 0) {
            int i = this.decrementStep();
            steps.get(i).getCompensation().accept(this);
        }
        if (this.getStep() >= 1) log.info("Saga finished with compensation");
    }

    public void addStep(Step placeOrder) {
        steps.add(placeOrder);
    }
}