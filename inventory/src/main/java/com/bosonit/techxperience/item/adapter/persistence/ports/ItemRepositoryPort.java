package com.bosonit.techxperience.item.adapter.persistence.ports;


import com.bosonit.techxperience.item.domain.Item;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ItemRepositoryPort extends PanacheRepository<Item> {
}
