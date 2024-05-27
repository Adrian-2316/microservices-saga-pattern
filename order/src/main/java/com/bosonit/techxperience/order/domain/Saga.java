package com.bosonit.techxperience.order.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Saga {
    private UUID id;
    private int step = 0;
    private boolean isCompensating = false;
    private int failurePercentage = 0;
}