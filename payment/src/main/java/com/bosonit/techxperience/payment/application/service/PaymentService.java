package com.bosonit.techxperience.payment.application.service;

import com.bosonit.techxperience.payment.adapter.persistence.ports.PaymentRepositoryPort;
import com.bosonit.techxperience.payment.application.ports.PaymentPort;
import com.bosonit.techxperience.payment.domain.Payment;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ApplicationScoped
public class PaymentService implements PaymentPort {

    @Inject
    PaymentRepositoryPort paymentRepositoryPort;

    @Override
    @WithTransaction
    public Uni<Payment> createPayment(Payment payment) {
        return paymentRepositoryPort.persistAndFlush(payment);
    }

    @Override
    @WithTransaction
    public Uni<Void> createAll(List<Payment> payments) {
        return Uni.combine().all().unis(payments.stream().map(pay -> paymentRepositoryPort.persist(pay)).toList())
                .discardItems();
    }

    @Override
    @WithTransaction
    public Uni<Payment> getPayment(long id) {
        return paymentRepositoryPort.findById(id);
    }

    @Override
    @WithTransaction
    public Uni<Payment> updatePayment(long id, Payment payment) {
        return paymentRepositoryPort.findById(id)
                .onItem().transform(p -> {
                    p.setAmount(payment.getAmount());
                    p.setCurrency(payment.getCurrency());
                    p.setPaymentDate(payment.getPaymentDate());
                    return p;
                })
                .onItem().transformToUni(paymentRepositoryPort::persistAndFlush);
    }

    @Override
    @WithTransaction
    public Uni<Void> deletePayment(long id) {
        return paymentRepositoryPort.deleteById(id).replaceWithVoid();
    }

    @Override
    @WithTransaction
    public Uni<List<Payment>> listPayments() {
        return paymentRepositoryPort.listAll();
    }
}
