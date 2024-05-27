package com.bosonit.techxperience.item.application.ports;

import com.bosonit.techxperience.item.domain.Item;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ItemPort {

    Uni<Item> createItem(Item item);

    Uni<Item> getItem(long id);

    Uni<Item> updateItem(long id, Item item);

    Uni<Void> deleteItem(long id);

    Uni<List<Item>> listItems();

    Uni<List<Item>> listItemsByAmount(int amount);

    Uni<Void> createAll(List<Item> items);
}
