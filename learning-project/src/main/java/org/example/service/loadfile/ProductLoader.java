package org.example.service.loadfile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.domain.Product;
import org.example.service.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ProductLoader implements LoadData {

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String PRICE_FIELD = "price";
    private static final String STOCK_AVAILABLE_FIELD = "stockAvailable";

    @Override
    public void loadData(String inputPath, String outputPath) {
        try (Reader reader = new FileReader(inputPath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            Set<Product> products = new HashSet<>();

            for (CSVRecord csvRecord : csvParser) {
                Product product = toProduct(csvRecord);
                products.add(product);
            }

            writeProductToFile(products, outputPath);
        } catch (IOException e) {
            Logger.log("Error in processing input file: " + inputPath);
        } catch (NumberFormatException e) {
            Logger.log("Error in parsing value of records in file: " + inputPath);
        } catch (IllegalArgumentException e) {
            Logger.log("Error in parsing fields in file: " + inputPath);
        } catch (Exception e) {
            Logger.log("Unknown error in reading file: " + inputPath);
        }
    }

    private Product toProduct(CSVRecord csvRecord) {
        String id = csvRecord.get(ID_FIELD);
        String name = csvRecord.get(NAME_FIELD);
        Double price = Double.valueOf(csvRecord.get(PRICE_FIELD));
        Integer stockAvailable = Integer.valueOf(csvRecord.get(STOCK_AVAILABLE_FIELD));
        return new Product(id, name, price, stockAvailable);
    }

    private void writeProductToFile(Set<Product> products, String outputPath) {
        try (Writer writer = new FileWriter(outputPath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(ID_FIELD, NAME_FIELD, PRICE_FIELD, STOCK_AVAILABLE_FIELD))) {

            for (Product product : products) {
                csvPrinter.printRecord(product.getId(), product.getName(), product.getPrice(), product.getStockAvailable());
            }
        } catch (IOException e) {

        }
    }

}
