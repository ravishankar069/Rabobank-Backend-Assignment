package com.rabo.customerstatementprocessorservice.util;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import com.rabo.customerstatementprocessorservice.service.CsvStatementReader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CsvStatementReaderTest {
    @InjectMocks
    CsvStatementReader csvStatementReader;

    List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();

    @Test
    public void tesingCsvParser() throws IOException {
        String file = new String("Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23");
        csvStatementReader.readRecords(file);
        TransactionRecord transactionRecord = new TransactionRecord("194261", "NL91RABO0315273637", "21.6", "Clothes from Jan Bakker", "-20.23", "-41.83");
        transactionRecordList.add(transactionRecord);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
}
