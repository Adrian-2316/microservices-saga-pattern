package com.bosonit.techxperience.item.adapter.rest.controller;

import com.bosonit.techxperience.item.application.ports.ItemPort;
import com.bosonit.techxperience.item.domain.Item;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Path("items")
@ApplicationScoped
public class ItemController {

    @Inject
    ItemPort itemPort;

    @GET
    public Uni<List<Item>> list() {
        log.info("Listing items");
        return itemPort.listItems();
    }

    @POST
    public Uni<Item> createItem(Item item) {
        log.info("Generating item");
        return itemPort.createItem(item);
    }

    @DELETE
    @Path("{id}")
    public Uni<Void> deleteItem(long id) {
        log.info("Delete item");
        return itemPort.deleteItem(id);
    }

    @GET
    @Path("{id}")
    public Uni<Item> getItem(long id) {
        log.info("Get item");
        return itemPort.getItem(id);
    }

    @PUT
    @Path("{id}")
    public Uni<Item> updateItem(long id, Item item) {
        log.info("Update item");
        return itemPort.updateItem(id, item);
    }
}
