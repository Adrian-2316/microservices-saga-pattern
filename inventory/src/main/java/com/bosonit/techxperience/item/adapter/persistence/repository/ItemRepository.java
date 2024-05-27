package com.bosonit.techxperience.item.adapter.persistence.repository;


import com.bosonit.techxperience.item.adapter.persistence.ports.ItemRepositoryPort;
import com.bosonit.techxperience.item.domain.Item;
import com.bosonit.techxperience.shared.StateEnum;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ItemRepository implements ItemRepositoryPort {
}
