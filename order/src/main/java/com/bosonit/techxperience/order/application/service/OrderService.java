package com.bosonit.techxperience.order.application.service;

import com.bosonit.techxperience.order.adapter.event_driven.ports.InventoryEventProducerPort;
import com.bosonit.techxperience.order.adapter.persistence.ports.OrderRepositoryPort;
import com.bosonit.techxperience.order.application.ports.OrderPort;
import com.bosonit.techxperience.order.domain.Order;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApplicationScoped
@Slf4j
public class OrderService implements OrderPort {

    @Inject
    OrderRepositoryPort orderRepositoryPort;
    @Inject
    InventoryEventProducerPort inventoryEventProducerPort;

    @Override
    public Uni<Order> placeOrder(Order order) {
        try {
            log.info("Placing order...");
            validateOrder(order);
            return createOrder(order).onItem().invoke(() -> inventoryEventProducerPort.reserveItems(order.getAmount()));
        } catch (Exception e) {
            log.error("Error while place order", e);
            return Uni.createFrom().failure(e);
        }
    }

    private void validateOrder(Order order) {
        log.info("Validating order: {}", order);
    }

    @Override
    @WithTransaction
    public Uni<String> applyOrder(Order order) {
        log.info("Aplicando orden");
        return Uni.createFrom().item("Orden procesado");
    }

    @Override
    @WithTransaction
    public Uni<Order> createOrder(Order order) {
        return orderRepositoryPort.persistAndFlush(order);
    }

    @Override
    @WithTransaction
    public Uni<Order> getOrder(long id) {
        return orderRepositoryPort.findById(id);
    }

    @Override
    @WithTransaction
    public Uni<Order> updateOrder(long id, Order order) {
        return orderRepositoryPort.findById(id)
                .onItem().transform(p -> {
                    p.setName(order.getName());
                    p.setClientId(order.getClientId());
                    p.setOrderDate(order.getOrderDate());
                    return p;
                })
                .onItem().transformToUni(orderRepositoryPort::persistAndFlush);
    }

    @Override
    @WithTransaction
    public Uni<Void> deleteOrder(long id) {
        return orderRepositoryPort.deleteById(id).replaceWithVoid();
    }

    @Override
    @WithTransaction
    public Uni<List<Order>> listOrders() {
        return orderRepositoryPort.listAll();
    }

    @Override
    @WithTransaction
    public Uni<Void> createAll(List<Order> orders) {
        return Uni.combine().all().unis(orders.stream().map(order -> orderRepositoryPort.persist(order)).toList())
                .discardItems();
    }

}
