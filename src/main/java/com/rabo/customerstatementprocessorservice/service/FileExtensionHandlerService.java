package com.rabo.customerstatementprocessorservice.service;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import com.rabo.customerstatementprocessorservice.utils.CsvStatementReader;
import com.rabo.customerstatementprocessorservice.utils.XmlStatementReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileExtensionHandlerService {
    private final Logger logger = LoggerFactory.getLogger(FileExtensionHandlerService.class);
    private XmlStatementReader xmlStatementReader;
    private CsvStatementReader csvStatementReader;

    @Autowired
    public FileExtensionHandlerService(XmlStatementReader xmlStatementReader,
                                       CsvStatementReader csvStatementReader) {
        this.xmlStatementReader = xmlStatementReader;
        this.csvStatementReader = csvStatementReader;
    }

    /**
     * This method used to redirect to respective parser based on file extension.
     *
     * @param MultipartFile
     * @return List<TransactionRecord>
     */
    public List<TransactionRecord> handleFileExtension(MultipartFile file) throws IOException {
        String fileAsString = new String(file.getBytes());
        if (file.getOriginalFilename().endsWith(".xml")) {
            logger.info("User uploaded the xml file statement");
            return xmlStatementReader.readRecords(fileAsString);
        } else {
            logger.info("User uploaded the csv file statement");
            return csvStatementReader.readRecords(fileAsString);
        }
    }
}
