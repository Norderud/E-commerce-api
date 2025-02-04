package no.norderud.bankid_ecommerceapi;

import no.norderud.bankid_ecommerceapi.repository.ProductRepository;
import no.norderud.bankid_ecommerceapi.service.CheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        ProductRepository productRepository = new ProductRepository();
        productRepository.init();
        checkoutService = new CheckoutService(productRepository);
    }

    @Test
    void testEmptyCartReturnsZero() {
        BigDecimal total = checkoutService.checkout(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, total, "An empty cart should result in a total of zero");
    }

    @Test
    void testSingleProductNoDiscount() {
        BigDecimal total = checkoutService.checkout(Collections.singletonList("0003"));
        assertEquals(new BigDecimal("400.00"), total);
    }

    @Test
    void testRolexDiscountExact() {
        BigDecimal total = checkoutService.checkout(Arrays.asList("0001", "0001", "0001"));
        assertEquals(new BigDecimal("2000.00"), total);
    }

    @Test
    void testRolexDiscountPlusOne() {
        BigDecimal total = checkoutService.checkout(Arrays.asList("0001", "0001", "0001", "0001"));
        assertEquals(new BigDecimal("3000.00"), total);
    }

    @Test
    void testMixedCart() {
        BigDecimal total = checkoutService.checkout(
                Arrays.asList("0001", "0001", "0001", "0002", "0002", "0004")
        );
        assertEquals(new BigDecimal("2150.00"), total);
    }

    @Test
    void testInvalidProductThrowsException() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                checkoutService.checkout(Collections.singletonList("9999"))
        );
        assertTrue(exception.getMessage().contains("Invalid product id"),
                "Exception message should indicate an invalid product id");
    }
}
