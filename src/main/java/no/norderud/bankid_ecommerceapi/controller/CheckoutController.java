package no.norderud.bankid_ecommerceapi.controller;

import no.norderud.bankid_ecommerceapi.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping(path = "/checkout", produces = "application/json")
    public ResponseEntity<String> checkout(@RequestBody List<String> productIds) {
        BigDecimal totalPrice = checkoutService.checkout(productIds);
        return ResponseEntity.ok(totalPrice.toString());
    }
}