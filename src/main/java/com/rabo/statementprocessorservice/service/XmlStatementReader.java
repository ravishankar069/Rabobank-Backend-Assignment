package com.rabo.statementprocessorservice.service;

import com.rabo.statementprocessorservice.modal.TransactionRecord;
import com.rabo.statementprocessorservice.modal.TransactionRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlStatementReader implements StatementReader {
    private final Logger logger = LoggerFactory.getLogger(XmlStatementReader.class);

    /**
     * This method objective is to parse the records from xml and stores it into list.
     *
     * @param String
     * @return List<TransactionRecord>
     */
    public List<TransactionRecord> readRecords(String file) {
        List<TransactionRecord> transactionRecordsList = new ArrayList<TransactionRecord>();
        try {
            logger.info("Starts parsing the xml statement");
            JAXBContext records = JAXBContext.newInstance(TransactionRecords.class);
            Unmarshaller recordsUnmarshaller = records.createUnmarshaller();
            TransactionRecords transactionRecords = (TransactionRecords) recordsUnmarshaller.unmarshal(new StringReader(file));
            for (TransactionRecord transactionRecord : transactionRecords.getTransactionRecord()) {
                transactionRecord.getTransactionReference();
                transactionRecord.getIban();
                transactionRecord.getStartBalance();
                transactionRecord.getDescription();
                transactionRecord.getMutation();
                transactionRecord.getEndBalance();
                transactionRecordsList.add(transactionRecord);
            }
        } catch (JAXBException parserException) {
            logger.error("Expected XML format missing");
        }
        return transactionRecordsList;
    }
}
