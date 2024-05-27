package com.bosonit.techxperience.order.adapter.persistence.ports;


import com.bosonit.techxperience.order.domain.Order;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

public interface OrderRepositoryPort extends PanacheRepository<Order> {

}
