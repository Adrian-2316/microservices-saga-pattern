package com.bosonit.techxperience.startup;

import com.bosonit.techxperience.order.application.ports.OrderPort;
import com.bosonit.techxperience.order.domain.Order;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.VertxContextSupport;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StartupInitializer {

    @Inject
    OrderPort orderPort;

    void onStart(@Observes StartupEvent ev) throws Throwable {
        VertxContextSupport.subscribeAndAwait(this::loadData);
    }


    public Uni<Void> loadData() {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.randomize();
            orders.add(order);
        }
        return orderPort.createAll(orders);
    }
}
