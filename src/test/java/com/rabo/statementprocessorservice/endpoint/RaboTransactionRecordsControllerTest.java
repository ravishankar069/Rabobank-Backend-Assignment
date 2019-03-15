package com.rabo.statementprocessorservice.endpoint;

import com.rabo.statementprocessorservice.service.CsvResponseWriter;
import com.rabo.statementprocessorservice.service.CsvStatementReader;
import com.rabo.statementprocessorservice.service.ValidatorService;
import com.rabo.statementprocessorservice.service.XmlResponseWriter;
import com.rabo.statementprocessorservice.service.XmlStatementReader;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;

@RunWith(MockitoJUnitRunner.class)
public class RaboTransactionRecordsControllerTest {
    @InjectMocks
    RaboTransactionRecordsController raboTransactionRecordsController;

    @Mock
    XmlStatementReader xmlStatementReader;
    @Mock
    ValidatorService validatorService;
    @Mock
    XmlResponseWriter xmlResponseWriter;
    @Mock
    CsvStatementReader csvStatementReader;
    @Mock
    CsvResponseWriter csvResponseWriter;

    @Test
    public void testingTheFlowOfInitiatXmlRecordsToValidatedRecord() throws Exception {
        File file = new File("src/test/records.xml");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        raboTransactionRecordsController.uploadFile(multipartFile);
        String validatedRecord = new String("<records>\r\n" + 
        		"  <record reference=\"130499\">\r\n" + 
        		"    <accountNumber>NL93ABNA0585619023</accountNumber>\r\n" + 
        		"    <description>Tickets from Erik de Vries</description>\r\n" + 
        		"    <startBalance>5429</startBalance>\r\n" + 
        		"    <mutation>-939</mutation>\r\n" + 
        		"    <endBalance>6368</endBalance>\r\n" + 
        		"    <status>Balance validation failed</status>\r\n" + 
        		"  </record>\r\n" + 
        		"</records>");
        Assert.assertEquals(validatedRecord, validatedRecord);
    }
    
    @Test
    public void testingTheFlowOfInitiatCsvRecordsToValidatedRecord() throws Exception {
        File file = new File("src/test/records.csv");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        raboTransactionRecordsController.uploadFile(multipartFile);
        String  validatedRecord = new String("reference,accountNumber,startBalance,description,endBalance,mutation,status\r\n" + 
        		"194261,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8,Duplicated References Records");
        Assert.assertEquals(validatedRecord, validatedRecord);
    }
    
    @Test
    public void testingTheCaseWhereTheFileIsNotInExpectedFormat() throws Exception {
        File file = new File("src/test/records.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        raboTransactionRecordsController.uploadFile(multipartFile);
        String validatedRecord = "File format will not be supported";
        Assert.assertEquals(validatedRecord, validatedRecord);
    }
}