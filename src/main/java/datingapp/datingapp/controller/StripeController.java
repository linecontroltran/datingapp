package datingapp.datingapp.controller;

import datingapp.datingapp.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    private final StripeService stripeService;

    @Autowired
    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-customer")
    public Map<String, String> createCustomer(@RequestBody Map<String, Object> customerMap) throws StripeException {
        String email = (String) customerMap.get("email");
        Customer customer = stripeService.createCustomer(email);
        Map<String, String> response = new HashMap<>();
        response.put("customerId", customer.getId());
        return response;
    }

    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> paymentMap) throws StripeException {
        Long amount = Long.valueOf((Integer) paymentMap.get("amount"));
        String currency = (String) paymentMap.get("currency");
        String customerId = (String) paymentMap.get("customerId");
        PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount, currency, customerId);
        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());
        return response;
    }
}
