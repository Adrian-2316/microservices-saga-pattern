package com.bosonit.techxperience.item.domain;

import com.bosonit.techxperience.shared.StateEnum;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item extends PanacheEntity {
    private String name;
    private int stock;
    private double price;
    private StateEnum state;

    @PrePersist
    public void prePersist() {
        if (this.stock == 0) this.stock = 10;
        if (this.price == 0) this.price = 100.0;
        if (this.name == null) this.name = "Item " + LocalDateTime.now();
    }


    public void randomize() {
        this.name = "Item " + LocalDateTime.now();
        this.stock = (int) (Math.random() * 100);
        this.price = Math.random() * 1000;
        this.state = StateEnum.values()[(int) (Math.random() * StateEnum.values().length)];
    }
}
