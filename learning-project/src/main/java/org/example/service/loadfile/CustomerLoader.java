package org.example.service.loadfile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.domain.Customer;
import org.example.service.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

public class CustomerLoader extends AbstractDataLoaderLoader<Customer> {

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String EMAIL_FIELD = "email";
    private static final String PHONE_NUMBER_FIELD = "phoneNumber";

    @Override
    protected Customer toEntity(CSVRecord csvRecord) {
        String id = csvRecord.get(ID_FIELD);
        String name = csvRecord.get(NAME_FIELD);
        String email = csvRecord.get(EMAIL_FIELD);
        String phoneNumber = csvRecord.get(PHONE_NUMBER_FIELD);

        return new Customer(id, name, email, phoneNumber);
    }

    @Override
    protected void writeProductToFile(Set<Customer> entities, String outputPath) {
        try(Writer writer = new FileWriter(outputPath);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(ID_FIELD, NAME_FIELD, EMAIL_FIELD, PHONE_NUMBER_FIELD))) {
            for(Customer customer: entities) {
                csvPrinter.printRecord(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber());
            }
        } catch (IOException e) {
            Logger.log("An error occurred while accessing output file: " + outputPath);
        } catch (Exception e) {
            Logger.log("An unknown error occurred while writing data to output file: " + outputPath);
        }
    }
}
