package org.example.service.loadfile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.domain.Order;
import org.example.service.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.OffsetDateTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;

public class OrderLoader extends AbstractDataLoaderLoader<Order> {

    private static final String ID_FIELD = "id";
    private static final String CUSTOMER_ID_FIELD = "customerId";
    private static final String ORDER_DATE_FIELD = "orderDate";
    private static final String PRODUCT_QUANTITIES_FIELD = "productQuantities";


    @Override
    protected Order toEntity(CSVRecord csvRecord) {
        String id = csvRecord.get(ID_FIELD);
        String customerId = csvRecord.get(CUSTOMER_ID_FIELD);
        OffsetDateTime orderDate = OffsetDateTime.parse(csvRecord.get(ORDER_DATE_FIELD));
        Dictionary<String, Integer> productQuantities = parseProductQuantities(csvRecord.get(PRODUCT_QUANTITIES_FIELD));

        return new Order(id, customerId, orderDate, productQuantities);
    }

    private Dictionary<String, Integer> parseProductQuantities(String productQuantities) {
        Hashtable<String, Integer> hashtable = new Hashtable<>();

        String productId = productQuantities.substring(0, productQuantities.lastIndexOf(":"));
        Integer quantities = Integer.valueOf(productQuantities.substring(productQuantities.lastIndexOf(":") + 1));
        hashtable.put(productId, quantities);

        return hashtable;
    }

    @Override
    protected void writeProductToFile(Set<Order> entities, String outputPath) {
        try(Writer writer = new FileWriter(outputPath);
                CSVPrinter csvPrinter = new CSVPrinter(writer,CSVFormat.DEFAULT.withHeader(ID_FIELD, CUSTOMER_ID_FIELD, ORDER_DATE_FIELD, PRODUCT_QUANTITIES_FIELD))) {
            for(Order order: entities) {
                csvPrinter.printRecord(order.getId(), order.getCustomerId(), order.getOrderDate(), order.getProductQuantities());
            }
        } catch (IOException e) {
            Logger.log("An error occurred while accessing output file: " + outputPath);
        } catch (Exception e) {
            Logger.log("An unknown error occurred while writing data to output file: " + outputPath);
        }
    }
}
