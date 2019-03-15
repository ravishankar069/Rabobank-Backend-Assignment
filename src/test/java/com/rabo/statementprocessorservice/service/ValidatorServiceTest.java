package com.rabo.customerstatementprocessorservice.service;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorServiceTest {
    @InjectMocks
    ValidatorService validatorService;

    List<TransactionRecord> transactionRecordList1 = new ArrayList<TransactionRecord>();
    List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();

    @Test
    public void testingValidation() {
        TransactionRecord transactionRecord1 = new TransactionRecord("130498", "NL69ABNA0433647324", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78");
        TransactionRecord transactionRecord2 = new TransactionRecord("130498", "NL69ABNA0433647324", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78");
        TransactionRecord transactionRecord3 = new TransactionRecord("147674", "NL69ABNA0433647324", "5429", "Tickets for Peter Theuß", "6368", "-939");
        transactionRecordList1.add(transactionRecord1);
        transactionRecordList1.add(transactionRecord2);
        transactionRecordList1.add(transactionRecord3);
        TransactionRecord transactionRecord = new TransactionRecord("130498", "NL69ABNA0433647324", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78");
        transactionRecordList.add(transactionRecord);
        validatorService.validateRecords(transactionRecordList1);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
}

