package com.bosonit.techxperience.order.application.ports;

import com.bosonit.techxperience.order.domain.Order;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface OrderPort {

    Uni<String> applyOrder(Order order);

    Uni<Order> createOrder(Order order);

    Uni<Order> getOrder(long id);

    Uni<Order> updateOrder(long id, Order order);

    Uni<Void> deleteOrder(long id);

    Uni<List<Order>> listOrders();

    Uni<Order> placeOrder(Order order);

    Uni<Void> createAll(List<Order> orders);
}
