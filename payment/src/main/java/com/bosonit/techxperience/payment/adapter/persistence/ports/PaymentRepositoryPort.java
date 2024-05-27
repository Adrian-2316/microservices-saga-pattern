package com.bosonit.techxperience.payment.adapter.persistence.ports;


import com.bosonit.techxperience.payment.domain.Payment;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import java.util.List;

public interface PaymentRepositoryPort extends PanacheRepository<Payment> {

}
