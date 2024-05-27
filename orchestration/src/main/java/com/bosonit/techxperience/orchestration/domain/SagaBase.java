package com.bosonit.techxperience.orchestration.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SagaBase {
    private UUID id = UUID.randomUUID();
    private int step = 0;
    private int failurePercentage;
    private boolean isCompensating = false;

    public SagaBase(SagaBase sagaBase) {
        this.id = sagaBase.id;
        this.step = sagaBase.step;
        this.isCompensating = sagaBase.isCompensating;
        this.failurePercentage = sagaBase.failurePercentage;
    }

    protected int incrementStep() {
        return step++;
    }

    protected int decrementStep() {
        return step--;
    }
}