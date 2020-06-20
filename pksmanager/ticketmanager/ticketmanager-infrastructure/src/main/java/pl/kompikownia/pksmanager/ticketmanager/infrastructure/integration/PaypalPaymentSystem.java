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
    public PaymentStatus generatePayment(String ticketPrice, String currency, String ticketId) {
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
        transaction.setCustom(ticketId);

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
               paymentStatus.setPaymentId(payment.getId());
//               paymentStatus.setPayerId(payer.getPayerInfo().getPayerId());
            }

        } catch (PayPalRESTException e) {
            log.error("Exception during payment: ",e);
            return PaymentStatus.builder()
                    .status("failure")
                    .build();
        }
        return paymentStatus;
    }

    @Override
    public String completePayment(String paymentId, String payerId) {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        try {
            APIContext context = new APIContext(clientId, clientSecret, mode);
            Payment createdPayment = payment.execute(context, paymentExecution);
            if (createdPayment != null) {
                return createdPayment.getTransactions().get(0).getCustom();
            }
        }
        catch(PayPalRESTException e) {
            e.printStackTrace();
        }
        return null;
    }
}
