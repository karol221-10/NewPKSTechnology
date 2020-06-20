package pl.kompikownia.pksmanager.ticketmanager.business.integration;

import pl.kompikownia.pksmanager.ticketmanager.business.integration.projection.PaymentStatus;

import java.math.BigDecimal;

public interface PaymentSystem {
    PaymentStatus generatePayment(String amount, String currency, String ticketId);
    String completePayment(String paymentId, String payerId);
}
