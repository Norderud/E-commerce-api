package no.norderud.bankid_ecommerceapi.controller;

import no.norderud.bankid_ecommerceapi.service.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckoutController {

    CheckoutService checkoutService;

    @GetMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody List<String> productIds) {
        checkoutService.checkout(productIds);
        return ResponseEntity.ok("Checkout");
    }
}
