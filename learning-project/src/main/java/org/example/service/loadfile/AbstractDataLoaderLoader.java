package org.example.service.loadfile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.service.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDataLoaderLoader<T> implements DataLoader {

    public void loadData(String inputPath, String outputPath) {
        try (Reader reader = new FileReader(inputPath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            Set<T> entities = new HashSet<>();

            for (CSVRecord csvRecord : csvParser) {
                T entity = toEntity(csvRecord);
                entities.add(entity);
            }

            writeProductToFile(entities, outputPath);
        } catch (IOException e) {
            Logger.log("An error occurred while accessing input file: " + inputPath);
        } catch (NumberFormatException e) {
            Logger.log("An error occurred while parsing value of records in file: " + inputPath);
        } catch (IllegalArgumentException e) {
            Logger.log("An error occurred while parsing fields in file: " + inputPath);
        } catch (Exception e) {
            Logger.log("An unknown error occurred while load data processing with file: " + inputPath);
        }
    }

    protected abstract T toEntity(CSVRecord csvRecord);

    protected abstract void writeProductToFile(Set<T> entities, String outputPath);
}
