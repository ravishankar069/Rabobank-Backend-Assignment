package com.rabo.statementprocessorservice.service;

import com.rabo.statementprocessorservice.modal.TransactionRecord;
import com.rabo.statementprocessorservice.utils.UniqueReferenceUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorServiceTest {
    @InjectMocks
    ValidatorService validatorService;
    @Mock
    UniqueReferenceUtility uniqueReferenceUtility;

    List<TransactionRecord> transactionRecordMockList = new ArrayList<TransactionRecord>();
    List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();

    @Test
    public void testingValidation() {
        TransactionRecord transactionRecord1 = new TransactionRecord("130498", "NL69ABNA0433647324", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78", null);
        TransactionRecord transactionRecord2 = new TransactionRecord("147674", "NL69ABNA0433647324", "5429", "Tickets for Peter Theuß", "6368", "-939",null);
        TransactionRecord transactionRecord3 = new TransactionRecord("130498", "NL69ABNA0433647325", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78",null);
        transactionRecordMockList.add(transactionRecord1);
        transactionRecordMockList.add(transactionRecord2);
        transactionRecordMockList.add(transactionRecord3);
        TransactionRecord transactionRecord4 = new TransactionRecord("147674", "NL69ABNA0433647324", "5429", "Tickets for Peter Theuß", "6368", "-939","Balance validation failed");
        TransactionRecord transactionRecord5 = new TransactionRecord("130498", "NL69ABNA0433647325", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78","Duplicated References Records");
        transactionRecordList.add(transactionRecord4);
        transactionRecordList.add(transactionRecord5);
        validatorService.validateRecords(transactionRecordMockList);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
}
