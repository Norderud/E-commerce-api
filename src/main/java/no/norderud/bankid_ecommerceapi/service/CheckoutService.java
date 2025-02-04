package no.norderud.bankid_ecommerceapi.service;

import no.norderud.bankid_ecommerceapi.model.Product;
import no.norderud.bankid_ecommerceapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CheckoutService {

    private final ProductRepository productRepository;

    public CheckoutService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BigDecimal checkout(List<String> productIds) {
        Map<String, Long> productCount = productIds.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<String, Long> entry : productCount.entrySet()) {
            String productId = entry.getKey();
            long quantity = entry.getValue();
            Product product = productRepository.getProductById(productId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product id: " + productId));
            total = total.add(calculateCost(product, quantity));
        }
        return total;
    }

    private BigDecimal calculateCost(Product product, long quantity) {
        if (product.discountThreshold() != null && product.discountPrice() != null) {
            long discountBundles = quantity / product.discountThreshold();
            long remainder = quantity % product.discountThreshold();

            return product.discountPrice().multiply(BigDecimal.valueOf(discountBundles))
                    .add(product.unitPrice().multiply(BigDecimal.valueOf(remainder)));
        } else {
            return product.unitPrice().multiply(BigDecimal.valueOf(quantity));
        }
    }

}