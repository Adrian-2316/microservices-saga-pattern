package com.bosonit.techxperience.payment.adapter.rest.controller;

import com.bosonit.techxperience.payment.application.ports.PaymentPort;
import com.bosonit.techxperience.payment.domain.Payment;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Path("payments")
@ApplicationScoped
public class PaymentController {

    @Inject
    PaymentPort paymentPort;

    @GET
    public Uni<List<Payment>> list() {
        log.info("Listing payments");
        return paymentPort.listPayments();
    }

    @POST
    public Uni<Payment> createPayment(Payment payment) {
        log.info("Generating payment");
        return paymentPort.createPayment(payment);
    }

    @DELETE
    @Path("{id}")
    public Uni<Void> deletePayment(long id) {
        log.info("Delete payment");
        return paymentPort.deletePayment(id);
    }

    @GET
    @Path("{id}")
    public Uni<Payment> getPayment(long id) {
        log.info("Get payment");
        return paymentPort.getPayment(id);
    }

    @PUT
    @Path("{id}")
    public Uni<Payment> updatePayment(long id, Payment payment) {
        log.info("Update payment");
        return paymentPort.updatePayment(id, payment);
    }
}
