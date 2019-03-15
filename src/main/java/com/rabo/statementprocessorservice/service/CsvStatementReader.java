package com.rabo.statementprocessorservice.service;

import com.opencsv.CSVReader;
import com.rabo.statementprocessorservice.modal.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
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
                readCsvStatement.close();
            }
        } catch (IOException ioException) {
            logger.error("Expected CSV format is missing");
        }
        return transactionRecordsList;
    }
}

