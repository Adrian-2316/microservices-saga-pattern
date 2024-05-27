package com.bosonit.techxperience.order.application.ports;

public interface OrderSagaPort {
    void placeOrder();

    void shipOrder();

    void cancelOrder();

    void cancelShipOrder();
}

