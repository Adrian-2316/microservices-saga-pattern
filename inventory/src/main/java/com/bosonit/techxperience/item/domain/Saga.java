package com.bosonit.techxperience.item.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Saga {
    private UUID id;
    private int step = 0;
    private boolean isCompensating = false;
    private int failurePercentage;
}