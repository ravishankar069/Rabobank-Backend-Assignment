package com.rabo.customerstatementprocessorservice.util;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import com.rabo.customerstatementprocessorservice.utils.CsvStatementReader;
import com.rabo.customerstatementprocessorservice.utils.XmlStatementReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class XmlStatementReaderTest {
    @InjectMocks
    XmlStatementReader xmlStatementReader;

    List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();

    @Test
    public void tesingXmlParser() throws IOException {
        String file = new String("<records>\n" +
                "  <record reference=\"130498\">\n" +
                "    <accountNumber>NL69ABNA0433647324</accountNumber>\n" +
                "    <description>Tickets for Peter Theuß</description>\n" +
                "    <startBalance>26.9</startBalance>\n" +
                "    <mutation>-18.78</mutation>\n" +
                "    <endBalance>8.12</endBalance>\n" +
                "  </record>\n" +
                "  </records>");
        xmlStatementReader.readRecords(file);
        TransactionRecord transactionRecord = new TransactionRecord("130498", "NL69ABNA0433647324", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78");
        transactionRecordList.add(transactionRecord);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
}
