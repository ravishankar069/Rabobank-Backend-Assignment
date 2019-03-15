package com.rabo.customerstatementprocessorservice.endpoint;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import com.rabo.customerstatementprocessorservice.service.ValidatorService;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RaboTransactionRecordsControllerTest {
    @InjectMocks
    RaboTransactionRecordsController raboTransactionRecordsController;

    @Mock
    ValidatorService validatorService;

    List<TransactionRecord> transactionRecordList = new ArrayList<TransactionRecord>();

    @Test
    public void testingTheFlowOfInitiatRecordsToValidatedRecord() throws Exception {
        File file = new File("src/test/records.xml");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        raboTransactionRecordsController.uploadFile(multipartFile);
        TransactionRecord transactionRecord = new TransactionRecord("130498", "NL69ABNA0433647324", "26.9", "Tickets for Peter Theuß", "8.12", "-18.78");;
        transactionRecordList.add(transactionRecord);
        Assert.assertEquals(transactionRecordList, transactionRecordList);
    }
}
