package org.example.domain;

import java.time.LocalDateTime;
import java.util.Dictionary;

public class Order {

    private String id;

    private String customerId;

    private LocalDateTime orderDate;

    private Dictionary<String, Integer> productQuantities;
}
