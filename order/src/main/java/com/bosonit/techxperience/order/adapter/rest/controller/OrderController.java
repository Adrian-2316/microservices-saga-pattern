package com.bosonit.techxperience.order.adapter.rest.controller;

import com.bosonit.techxperience.order.application.ports.OrderPort;
import com.bosonit.techxperience.order.domain.Order;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Path("orders")
@ApplicationScoped
public class OrderController {

    @Inject
    OrderPort orderPort;

    @POST
    @Path("place-order")
    public Uni<Order> placeOrder(Order order) {
        log.info("New order recived");
        return orderPort.placeOrder(order);
    }

    @GET
    public Uni<List<Order>> list() {
        log.info("Listing orders");
        return orderPort.listOrders();
    }

    @POST
    public Uni<Order> createOrder(Order order) {
        log.info("Generating order");
        return orderPort.createOrder(order);
    }

    @DELETE
    @Path("{id}")
    public Uni<Void> deleteOrder(long id) {
        log.info("Delete order");
        return orderPort.deleteOrder(id);
    }

    @GET
    @Path("{id}")
    public Uni<Order> getOrder(long id) {
        log.info("Get order");
        return orderPort.getOrder(id);
    }

    @PUT
    @Path("{id}")
    public Uni<Order> updateOrder(long id, Order order) {
        log.info("Update order");
        return orderPort.updateOrder(id, order);
    }
}
