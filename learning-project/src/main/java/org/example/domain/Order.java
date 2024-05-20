package org.example.domain;

import java.time.OffsetDateTime;
import java.util.Dictionary;

public class Order {

    private String id;

    private String customerId;

    private OffsetDateTime orderDate;

    private Dictionary<String, Integer> productQuantities;

    public Order(String id, String customerId, OffsetDateTime orderDate, Dictionary<String, Integer> productQuantities) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.productQuantities = productQuantities;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OffsetDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(OffsetDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Dictionary<String, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Dictionary<String, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }
}
