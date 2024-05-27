package com.bosonit.techxperience.payment.application.service;

import com.bosonit.techxperience.payment.application.ports.PaymentSagaPort;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class PaymentSagaService implements PaymentSagaPort {
    @Override
    public void processPayment() {

    }


    @Override
    public void cancelPayment() {

    }

}
