package com.bosonit.techxperience.item.application.service;

import com.bosonit.techxperience.item.application.ports.ItemPort;
import com.bosonit.techxperience.item.application.ports.ItemSagaPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.Random;

@Slf4j
@ApplicationScoped
public class ItemSagaService implements ItemSagaPort {

    @Inject
    ItemPort itemPort;

    @Override
    @Retry(maxRetries = 3)
    public void reserveItems(int failurePercentage) {
        Random random = new Random();
        if (random.nextInt(100) < failurePercentage) {
            log.error("[SAGA] Failed to reserve items");
            throw new RuntimeException("Failed to reserve items");
        }
    }

    @Override
    public void removeInventory() {
    }

    @Override
    public void restockInventory() {
    }

    @Override
    public void unlockInventory() {
    }
}
