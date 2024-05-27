package com.bosonit.techxperience.startup;

import com.bosonit.techxperience.item.application.ports.ItemPort;
import com.bosonit.techxperience.item.domain.Item;
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
    ItemPort itemPort;

    void onStart(@Observes StartupEvent ev) throws Throwable {
        VertxContextSupport.subscribeAndAwait(this::loadData);
    }


    public Uni<Void> loadData() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.randomize();
            items.add(item);
        }
        return itemPort.createAll(items);
    }
}
