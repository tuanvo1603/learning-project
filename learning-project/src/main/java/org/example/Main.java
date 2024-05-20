package org.example;

import org.example.domain.Customer;
import org.example.domain.Order;
import org.example.domain.Product;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Main {

    private Set<Product> products = new HashSet<>();
    private Set<Customer> customers = new HashSet<>();
    private Set<Order> orders = new HashSet<>();
    private String processingFolder;

    public static void main(String[] args) {
        System.out.println(OffsetDateTime.parse("2024-02-06T13:40:10.9797779+07:00"));
    }
}