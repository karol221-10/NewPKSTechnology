package pl.kompikownia.pksmanager.ticketmanager.business.integration;

import pl.kompikownia.pksmanager.ticketmanager.business.integration.projection.PaymentStatus;

import java.math.BigDecimal;

public interface PaymentSystem {
    PaymentStatus generatePayment(String amount, String currency);
}
