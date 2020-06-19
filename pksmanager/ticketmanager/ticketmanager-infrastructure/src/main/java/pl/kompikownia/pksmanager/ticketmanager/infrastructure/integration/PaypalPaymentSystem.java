package pl.kompikownia.pksmanager.ticketmanager.infrastructure.integration;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.kompikownia.pksmanager.ticketmanager.business.integration.PaymentSystem;
import pl.kompikownia.pksmanager.ticketmanager.business.integration.projection.PaymentStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PaypalPaymentSystem implements PaymentSystem {

    @Value("${pl.kompikownia.pksmanager.ticketmanager.integration.paymentsystem.paypal.cancelurl}")
    private String cancelUrl;

    @Value("${pl.kompikownia.pksmanager.ticketmanager.integration.paymentsystem.paypal.returnurl}")
    private String returnUrl;

    @Value("${pl.kompikownia.pksmanager.ticketmanager.integration.paymentsystem.paypal.clientId}")
    private String clientId;

    @Value("${pl.kompikownia.pksmanager.ticketmanager.integration.paymentsystem.paypal.clientSecret}")
    private String clientSecret;

    @Value("${pl.kompikownia.pksmanager.ticketmanager.integration.paymentsystem.paypal.mode}")
    private String mode;

    @Override
    public PaymentStatus generatePayment(String ticketPrice, String currency) {
        PaymentStatus paymentStatus = new PaymentStatus();
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(ticketPrice);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        val transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(returnUrl);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            APIContext context = new APIContext(clientId, clientSecret, mode);
            createdPayment = payment.create(context);
            if (createdPayment != null) {
               List<Links> links = createdPayment.getLinks();
               links.forEach(link -> {
                   if(link.getRel().equals("approval_url")) {
                       paymentStatus.setRedirectUrl(link.getHref());
                   }
               });
               paymentStatus.setStatus("success");
            }

        } catch (PayPalRESTException e) {
            log.error("Exception during payment: ",e);
            return PaymentStatus.builder()
                    .status("failure")
                    .build();
        }
        return paymentStatus;
    }
}
