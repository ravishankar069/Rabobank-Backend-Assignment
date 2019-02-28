package com.rabo.customerstatementprocessorservice.utils;

import com.opencsv.CSVReader;
import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CsvStatementReader implements StatementReader {
    private final Logger logger = LoggerFactory.getLogger(CsvStatementReader.class);

    /**
     * This method objective is to parse the records from csv and stores it into list.
     *
     * @param String
     * @return List<TransactionRecord>
     */
    public List<TransactionRecord> readRecords(String file) {
        List<TransactionRecord> transactionRecordsList = new ArrayList<TransactionRecord>();
        try {
            logger.info("Starts parsing the csv statement");
            CSVReader readCsvStatement = new CSVReader(new StringReader(file));
            List<String[]> records = readCsvStatement.readAll();
            Iterator<String[]> iterator = records.iterator();
            iterator.next();
            while (iterator.hasNext()) {
                String[] record = iterator.next();
                TransactionRecord transactionRecord = new TransactionRecord();
                transactionRecord.setTransactionReference(record[0]);
                transactionRecord.setIban(record[1]);
                transactionRecord.setDescription(record[2]);
                transactionRecord.setStartBalance(record[3]);
                transactionRecord.setMutation(record[4]);
                transactionRecord.setEndBalance(record[5]);
                transactionRecordsList.add(transactionRecord);
            }
        } catch (FileNotFoundException invalidFileException) {
            invalidFileException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return transactionRecordsList;
    }
}

