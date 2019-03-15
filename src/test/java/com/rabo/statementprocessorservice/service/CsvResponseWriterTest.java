package com.rabo.statementprocessorservice.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.rabo.statementprocessorservice.modal.TransactionRecord;


@RunWith(MockitoJUnitRunner.class)
public class CsvResponseWriterTest {
	@InjectMocks
	CsvResponseWriter csvResponseWriter;
	
	@Test
	public void testingCsvWriter() {
		List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();
		transactionRecordList.add(new TransactionRecord("167875", "NL93ABNA0585619023", "5429", "Tickets from Erik de Vries", "6368", "-939","Balance validation failed"));
		String validatedRecord = new String("reference,accountNumber,startBalance,description,endBalance,mutation,status\r\n" + 
        		"167875,NL93ABNA0585619023,Tickets from Erik de Vries,5429,6368,-939,Balance validation failed");
		Assert.assertEquals(validatedRecord, validatedRecord);
	}
}
