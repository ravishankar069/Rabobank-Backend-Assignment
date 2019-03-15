package com.rabo.statementprocessorservice.endpoint;

import com.rabo.statementprocessorservice.modal.FileFormat;
import com.rabo.statementprocessorservice.service.CsvResponseWriter;
import com.rabo.statementprocessorservice.service.CsvStatementReader;
import com.rabo.statementprocessorservice.service.ValidatorService;
import com.rabo.statementprocessorservice.service.XmlResponseWriter;
import com.rabo.statementprocessorservice.service.XmlStatementReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
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

    /**
     *Checks whether the expected file format is given by the user and if yes,starts validation. 
     *
     *@param(MultipartFile)
     *return String of expected format
     */
    @PostMapping(value="/rabo/customerstatement")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException,JAXBException {
    	String fileAsString = new String(file.getBytes());
        if(FileFormat.notify(file)) {
    	if (file.getOriginalFilename().endsWith(".xml")) {
            return xmlResponseWriter.writeRecords(validatorService.validateRecords
            		(xmlStatementReader.readRecords(fileAsString)));
        } else {
        	return csvResponseWriter.writeRecords(validatorService.validateRecords
            		(csvStatementReader.readRecords(fileAsString)));
        }
        }else {
        	return "File format will not be supported";
        }
    }
}