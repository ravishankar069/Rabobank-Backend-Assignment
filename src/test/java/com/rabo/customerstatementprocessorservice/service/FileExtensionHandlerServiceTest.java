package com.rabo.customerstatementprocessorservice.service;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import com.rabo.customerstatementprocessorservice.utils.CsvStatementReader;
import com.rabo.customerstatementprocessorservice.utils.XmlStatementReader;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FileExtensionHandlerServiceTest {
    @InjectMocks
    FileExtensionHandlerService fileExtensionHandlerService;

    @Mock
    XmlStatementReader xmlStatementReader;

    @Mock
    CsvStatementReader csvStatementReader;

    List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();

    @Test
    public void testingExtensionHandler() throws IOException{
        File file = new File("src/test/records.csv");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        fileExtensionHandlerService.handleFileExtension(multipartFile);
        TransactionRecord transactionRecord = new TransactionRecord("194261", "NL91RABO0315273637", "21.6", "Clothes from Jan Bakker", "-20.23", "-41.83");
        transactionRecordList.add(transactionRecord);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
}
