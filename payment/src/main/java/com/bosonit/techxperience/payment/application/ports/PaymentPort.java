package com.bosonit.techxperience.payment.application.ports;

import com.bosonit.techxperience.payment.domain.Payment;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface PaymentPort {

    Uni<Payment> createPayment(Payment payment);

    Uni<Payment> getPayment(long id);

    Uni<Payment> updatePayment(long id, Payment payment);

    Uni<Void> deletePayment(long id);

    Uni<List<Payment>> listPayments();

    Uni<Void> createAll(List<Payment> payments);
}
