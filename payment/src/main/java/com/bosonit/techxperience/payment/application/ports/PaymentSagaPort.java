package com.bosonit.techxperience.payment.application.ports;

import com.bosonit.techxperience.payment.domain.Payment;
import com.bosonit.techxperience.payment.domain.Saga;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface PaymentSagaPort {
    void processPayment();

    void cancelPayment();
}
