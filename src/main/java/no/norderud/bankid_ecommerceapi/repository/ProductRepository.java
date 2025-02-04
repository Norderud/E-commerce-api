package no.norderud.bankid_ecommerceapi.repository;

import jakarta.annotation.PostConstruct;
import no.norderud.bankid_ecommerceapi.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    @PostConstruct
    public void init() {
        products.put("0001", new Product("0001", "Rolex Watch", new BigDecimal("1000.00"), 3, new BigDecimal("2000.00")));
        products.put("0002", new Product("0002", "Michael Kors Purse", new BigDecimal("80.00"), 2, new BigDecimal("120.00")));
        products.put("0003", new Product("0003", "iPhone XS", new BigDecimal("400.00"), null, null));
        products.put("0004", new Product("0004", "Casio Watch", new BigDecimal("30.00"), null, null));
    }

    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(products.get(id));
    }
}