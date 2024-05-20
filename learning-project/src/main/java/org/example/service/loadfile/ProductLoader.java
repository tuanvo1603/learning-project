package org.example.service.loadfile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.domain.Product;
import org.example.service.Logger;

import java.io.*;
import java.util.Set;

public class ProductLoader extends AbstractDataLoaderLoader<Product> {

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String PRICE_FIELD = "price";
    private static final String STOCK_AVAILABLE_FIELD = "stockAvailable";

    @Override
    protected Product toEntity(CSVRecord csvRecord) {
        String id = csvRecord.get(ID_FIELD);
        String name = csvRecord.get(NAME_FIELD);
        Double price = Double.valueOf(csvRecord.get(PRICE_FIELD));
        Integer stockAvailable = Integer.valueOf(csvRecord.get(STOCK_AVAILABLE_FIELD));

        return new Product(id, name, price, stockAvailable);
    }

    @Override
    protected void writeProductToFile(Set<Product> products, String outputPath) {
        try (Writer writer = new FileWriter(outputPath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(ID_FIELD, NAME_FIELD, PRICE_FIELD, STOCK_AVAILABLE_FIELD))) {
            for (Product product : products) {
                csvPrinter.printRecord(product.getId(), product.getName(), product.getPrice(), product.getStockAvailable());
            }
        } catch (IOException e) {
            Logger.log("An error occurred while accessing output file: " + outputPath);
        } catch (Exception e) {
            Logger.log("An error occurred while writing data to output file: " + outputPath);
        }
    }

}
