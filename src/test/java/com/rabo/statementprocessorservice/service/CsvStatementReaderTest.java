package com.rabo.statementprocessorservice.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.rabo.statementprocessorservice.modal.TransactionRecord;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class CsvStatementReaderTest {
    @InjectMocks
    CsvStatementReader csvStatementReader;

    List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();

    @Test
    public void tesingCsvParser(){
        String file = new String("Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23");
        csvStatementReader.readRecords(file);
        TransactionRecord transactionRecord = new TransactionRecord("194261", "NL91RABO0315273637", "21.6", "Clothes from Jan Bakker", "-20.23", "-41.83", null);
        transactionRecordList.add(transactionRecord);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
    
    @Test
    public void tesingCsvParserChange(){
        String file = new String("AccountNumber,Reference,Description,Start Balance,Mutation,End Balance\nNL91RABO0315273637,194261,Clothes from Jan Bakker,21.6,-41.83,-20.23");
        csvStatementReader.readRecords(file);
        TransactionRecord transactionRecord = new TransactionRecord("194261", "NL91RABO0315273637", "21.6", "Clothes from Jan Bakker", "-20.23", "-41.83", null);
        transactionRecordList.add(transactionRecord);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
}