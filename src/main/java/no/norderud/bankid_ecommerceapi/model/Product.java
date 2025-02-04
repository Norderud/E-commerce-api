package no.norderud.bankid_ecommerceapi.model;

import java.math.BigDecimal;

public record Product(String id, String name, BigDecimal unitPrice, Integer discountThreshold,
                      BigDecimal discountPrice) {

}
