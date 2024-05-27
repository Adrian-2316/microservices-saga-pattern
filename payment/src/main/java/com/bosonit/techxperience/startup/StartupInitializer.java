package com.bosonit.techxperience.startup;

import com.bosonit.techxperience.payment.application.ports.PaymentPort;
import com.bosonit.techxperience.payment.domain.Payment;
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
    PaymentPort paymentPort;

    void onStart(@Observes StartupEvent ev) throws Throwable {
        VertxContextSupport.subscribeAndAwait(this::loadData);
    }


    public Uni<Void> loadData() {
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Payment payment = new Payment();
            payment.randomize();
            payments.add(payment);
        }
        return paymentPort.createAll(payments);
    }
}
