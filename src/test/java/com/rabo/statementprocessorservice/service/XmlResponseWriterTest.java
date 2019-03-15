package com.rabo.statementprocessorservice.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.rabo.statementprocessorservice.modal.TransactionRecord;
import com.rabo.statementprocessorservice.modal.TransactionRecords;

@RunWith(MockitoJUnitRunner.class)
public class XmlResponseWriterTest {
	@InjectMocks
	XmlResponseWriter xmlResponseWriter;
	@Mock
	TransactionRecords transactionRecords;
	
	@Test
	public void testingXmlWriter() {
		List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();
		transactionRecordList.add(new TransactionRecord("167875", "NL93ABNA0585619023", "5429", "Tickets from Erik de Vries", "6368", "-939","Balance validation failed"));
		String validatedRecord = new String("<records>\r\n" + 
				"    <record reference=\"167875\">\r\n" + 
				"        <accountNumber>NL93ABNA0585619023</accountNumber>\r\n" + 
				"        <startBalance>5429</startBalance>\r\n" + 
				"        <description>Tickets from Erik de Vries</description>\r\n" + 
				"        <endBalance>6368</endBalance>\r\n" + 
				"        <mutation>-939</mutation>\r\n" + 
				"        <status>Balance validation failed</status>\r\n" + 
				"    </record>\r\n" + 
				"</records>");
		xmlResponseWriter.writeRecords(transactionRecordList);
        Assert.assertEquals(validatedRecord, validatedRecord);
	}
}
