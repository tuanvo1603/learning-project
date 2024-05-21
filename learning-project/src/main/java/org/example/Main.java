package org.example;

import org.example.service.loadfile.CustomerLoader;
import org.example.service.loadfile.DataLoader;
import org.example.service.loadfile.OrderLoader;
import org.example.service.loadfile.ProductLoader;

public class Main {

    private static final String LOAD_DATA_FUNCTION = "1";

    public static void main(String[] args) {
        String function = args[0];
        String processingFolderPath = args[1];
        String inputFolderPath = processingFolderPath + "\\" + "InputFolder" + "\\";
        String outputFolderPath = processingFolderPath + "\\" + "OutputFolder" + "\\";

        switch (function) {
            case LOAD_DATA_FUNCTION -> {
                DataLoader productLoader = new ProductLoader();
                DataLoader orderLoader = new OrderLoader();
                DataLoader customerLoader = new CustomerLoader();
                productLoader.loadData(inputFolderPath + "products.origin.csv", outputFolderPath + "products.output.csv");
                orderLoader.loadData(inputFolderPath + "orders.origin.csv", outputFolderPath + "orders.output.csv");
                customerLoader.loadData(inputFolderPath + "customers.origin.csv", outputFolderPath + "customers.output.csv");
            }
        }
    }
}