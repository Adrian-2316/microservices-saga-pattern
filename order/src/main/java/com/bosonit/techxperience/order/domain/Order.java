package com.bosonit.techxperience.order.domain;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends PanacheEntity {
    private String name;
    private String clientId;
    private Date orderDate;
    @Transient
    private Integer amount;

    @PrePersist
    public void prePersist() {
        if (orderDate == null) orderDate = new Date();
        if (name == null) name = "Order " + LocalDateTime.now();
    }

    public void randomize() {
        name = "Order " + LocalDateTime.now();
        clientId = "Client " + (int) (Math.random() * 1000);
        orderDate = new Date();
        amount = (int) (Math.random() * 1000);
    }
}
