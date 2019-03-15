package com.rabo.statementprocessorservice.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.rabo.statementprocessorservice.modal.TransactionRecord;

@Service
public class CsvResponseWriter implements ResponseWriter{
	private final Logger logger = LoggerFactory.getLogger(CsvResponseWriter.class);

	/**
     * This method objective is to parse the records from object format to csv.
     *
     * @param List<TransactionRecord>
     * @return String of csv format
     */
	public String writeRecords(List<TransactionRecord> transactionRecord){
		StringWriter stringWriter = new StringWriter();
		CSVPrinter csvPrinter;
		try {
		logger.info("Starts parsing into csv format");	
		csvPrinter = new CSVPrinter(stringWriter, CSVFormat.DEFAULT.withHeader(
	            "reference", "accountNumber", "startBalance", "description", "endBalance","mutation","status"));
	    for (TransactionRecord records : transactionRecord) {
	        csvPrinter.printRecord(
	        		records.getTransactionReference(),
	        		records.getIban(),
	        		records.getStartBalance(),
	        		records.getDescription(),
	        		records.getEndBalance(),
	        		records.getMutation(),
	        		records.getStatus()
	        );
	    }
		}catch(IOException ioException) {
			logger.error("Unable to produce the csv format");
		}
	    stringWriter.flush();
	    return stringWriter.toString();
	}
}
