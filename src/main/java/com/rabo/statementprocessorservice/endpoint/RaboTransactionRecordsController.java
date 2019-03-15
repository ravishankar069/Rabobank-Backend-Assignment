package com.rabo.customerstatementprocessorservice.endpoint;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import com.rabo.customerstatementprocessorservice.service.CsvResponseWriter;
import com.rabo.customerstatementprocessorservice.service.CsvStatementReader;
import com.rabo.customerstatementprocessorservice.service.ValidatorService;
import com.rabo.customerstatementprocessorservice.service.XmlResponseWriter;
import com.rabo.customerstatementprocessorservice.service.XmlStatementReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;


/**
 * RaboTransactionRecordsController enables the user to posting the customer statement to service
 *
 * @author Ravisankar Rajamanickam
 */

@RestController
public class RaboTransactionRecordsController {
	private XmlResponseWriter xmlResponseWriter;
	private CsvResponseWriter csvResponseWriter;
    private XmlStatementReader xmlStatementReader;
    private CsvStatementReader csvStatementReader;
    private ValidatorService validatorService;

    @Autowired
    RaboTransactionRecordsController(ValidatorService validatorService,
    								 XmlStatementReader xmlStatementReader,
    								 CsvStatementReader csvStatementReader,
    								 XmlResponseWriter xmlResponseWriter,
    								 CsvResponseWriter csvResponseWriter) {
        this.validatorService = validatorService;
        this.xmlStatementReader = xmlStatementReader;
        this.csvStatementReader = csvStatementReader;
        this.xmlResponseWriter = xmlResponseWriter;
        this.csvResponseWriter = csvResponseWriter;
    }

    @PostMapping(value="/rabo/customerstatement")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException,JAXBException {
    	String fileAsString = new String(file.getBytes());
        if (file.getOriginalFilename().endsWith(".xml")) {
            List<TransactionRecord> validatedRecords = validatorService.validateRecords
            		(xmlStatementReader.readRecords(fileAsString));
            return xmlResponseWriter.writeRecords(validatedRecords);
        } else {
            List<TransactionRecord> validatedRecords = validatorService.validateRecords
            		(csvStatementReader.readRecords(fileAsString));
        	return csvResponseWriter.writeRecords(validatedRecords);
        }
    }
}