package com.bosonit.techxperience.payment.domain;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends PanacheEntity {
    private LocalDateTime paymentDate = LocalDateTime.now();
    private Double amount = 1000.0;
    private String description = "Payment for the order";
    private String status = "Pending";
    private String currency = "USD";
    private String paymentMethod = "Credit Card";
    private String paymentName = "John Doe";

    @PrePersist
    public void prePersist() {
        if (this.paymentDate == null) this.paymentDate = LocalDateTime.now();
        if (this.amount == null) this.amount = 1000.0;
        if (this.currency == null) this.currency = "USD";
        if (this.paymentMethod == null) this.paymentMethod = "Credit Card";
        if (this.paymentName == null) this.paymentName = "John Doe";
        if (this.description == null) this.description = "Payment for the order";
        if (this.status == null) this.status = "Pending";
    }

    public void randomize() {
        List<String> methodPool = List.of("Credit Card", "Paypal", "Stripe", "Apple Pay", "Google Pay", "Amazon Pay", "Venmo", "Cash", "Check");
        List<String> currencyPool = List.of("USD", "EUR", "GBP", "JPY", "CNY", "INR", "RUB", "BRL", "MXN", "CAD");
        List<String> statusPool = List.of("Pending", "Paid", "Failed", "Refunded", "Cancelled", "Authorized", "Captured");
        List<String> descriptionPool = List.of("Payment for the order", "Refund for the order", "Authorization for the order", "Capture for the order", "Payment for the invoice", "Refund for the invoice", "Authorization for the invoice", "Capture for the invoice");
        List<String> paymentNamePool = List.of("Visa", "Mastercard", "American Express", "Discover", "Paypal", "Stripe", "Apple Pay", "Google Pay", "Amazon Pay", "Venmo", "Cash", "Check");
        this.paymentDate = LocalDateTime.now();
        this.amount = Math.random() * 1000;
        this.currency = currencyPool.get((int) (Math.random() * currencyPool.size()));
        this.paymentMethod = methodPool.get((int) (Math.random() * methodPool.size()));
        this.paymentName = paymentNamePool.get((int) (Math.random() * paymentNamePool.size()));
        this.description = descriptionPool.get((int) (Math.random() * descriptionPool.size()));
        this.status = statusPool.get((int) (Math.random() * statusPool.size()));
    }
}
