package com.bosonit.techxperience.item.application.service;

import com.bosonit.techxperience.item.adapter.persistence.ports.ItemRepositoryPort;
import com.bosonit.techxperience.item.application.ports.ItemPort;
import com.bosonit.techxperience.item.domain.Item;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ApplicationScoped
public class ItemService implements ItemPort {

    @Inject
    ItemRepositoryPort itemRepositoryPort;

    @Override
    @WithTransaction
    public Uni<Item> createItem(Item item) {
        return itemRepositoryPort.persistAndFlush(item);
    }

    @Override
    @WithTransaction
    public Uni<Item> getItem(long id) {
        return itemRepositoryPort.findById(id);
    }

    @Override
    @WithTransaction
    public Uni<Item> updateItem(long id, Item item) {
        return itemRepositoryPort.findById(id)
                .onItem().transform(p -> {
                    p.setName(item.getName());
                    p.setPrice(item.getPrice());
                    p.setStock(item.getStock());
                    return p;
                })
                .onItem().transformToUni(itemRepositoryPort::persistAndFlush);
    }

    @Override
    @WithTransaction
    public Uni<Void> deleteItem(long id) {
        return itemRepositoryPort.deleteById(id).replaceWithVoid();
    }

    @Override
    @WithTransaction
    public Uni<List<Item>> listItems() {
        return itemRepositoryPort.listAll();
    }

    @Override
    @WithTransaction
    public Uni<List<Item>> listItemsByAmount(int amount) {
        return Item.findAll().page(0, amount).list();
    }


    @Override
    @WithTransaction
    public Uni<Void> createAll(List<Item> items) {
        return Uni.combine().all().unis(items.stream().map(item -> itemRepositoryPort.persist(item)).toList())
                .discardItems();
    }
}
