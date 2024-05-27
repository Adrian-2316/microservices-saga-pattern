package com.bosonit.techxperience.item.application.ports;

public interface ItemSagaPort {

    void reserveItems(int failurePercentage) throws Exception;

    void removeInventory();

    void restockInventory();

    void unlockInventory();
}
