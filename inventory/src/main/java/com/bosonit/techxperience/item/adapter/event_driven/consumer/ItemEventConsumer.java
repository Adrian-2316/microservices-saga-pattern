package com.bosonit.techxperience.item.adapter.event_driven.consumer;

import com.bosonit.techxperience.item.adapter.event_driven.ports.ItemEventProducerPort;
import com.bosonit.techxperience.item.application.ports.ItemSagaPort;
import com.bosonit.techxperience.item.domain.Saga;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.time.Duration;

@Slf4j
@ApplicationScoped
public class ItemEventConsumer {
    private static final int MAX_RETRIES = 3;
    private static final Duration INITIAL_BACKOFF_DELAY = Duration.ofSeconds(1);
    private static final Duration MAX_BACKOFF_DELAY = Duration.ofMinutes(1);
    @Inject
    ItemSagaPort itemSagaPort;

    @Inject
    ItemEventProducerPort itemProducerPort;

    @Incoming("inventory-reserve")
    @NonBlocking
    public void reserveItems(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing reserving items event".formatted(saga.getId(), saga.getStep()));
        try {
            itemSagaPort.reserveItems(saga.getFailurePercentage());
        } catch (Exception e) {
            saga.setCompensating(true);
        }
        itemProducerPort.reserveInventoryResponse(saga);
    }

    @Incoming("inventory-remove")
    @NonBlocking
    public void removeInventory(Saga saga) {
        log.info("[SAGA %s] STEP %s - Processing removing items event".formatted(saga.getId(), saga.getStep()));
        try {
            itemSagaPort.removeInventory();
        } catch (Exception e) {
            saga.setCompensating(true);
        }
        itemProducerPort.removeInventoryResponse(saga);
    }

    @Incoming("inventory-restock")
    @NonBlocking
    public void restockInventory(Saga saga) {
        log.info("[SAGA %s] COMPENSATION %s - Processing restocking items event".formatted(saga.getId(), saga.getStep()));
        itemSagaPort.restockInventory();
    }

    @Incoming("inventory-unlock")
    @NonBlocking
    public void unlockInventory(Saga saga) {
        log.info("[SAGA %s] COMPENSATION %s - Processing unlocking items event".formatted(saga.getId(), saga.getStep()));
        itemSagaPort.unlockInventory();
    }
}
