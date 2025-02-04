package no.norderud.bankid_ecommerceapi.model;


import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private Integer discountThreshold;
    private BigDecimal discountPrice;

    public Product(String id, String name, BigDecimal unitPrice, Integer discountThreshold, BigDecimal discountPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.discountThreshold = discountThreshold;
        this.discountPrice = discountPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getDiscountThreshold() {
        return discountThreshold;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
}
