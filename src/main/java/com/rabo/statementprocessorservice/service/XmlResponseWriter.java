package com.rabo.statementprocessorservice.service;

import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rabo.statementprocessorservice.modal.TransactionRecord;
import com.rabo.statementprocessorservice.modal.TransactionRecords;

@Service
public class XmlResponseWriter implements ResponseWriter{
	private final Logger logger = LoggerFactory.getLogger(XmlResponseWriter.class);
	@Autowired
	TransactionRecords transactionRecords;
	
	/**
     * This method objective is to parse the records from object format to xml.
     *
     * @param List<TransactionRecord>
     * @return String of xml format
     */
	public String writeRecords(List<TransactionRecord> transactionRecord){
		String validatedrecords = "";
		try {
		logger.info("Starts parsing the validated records to xml format");
		JAXBContext jaxbContext = JAXBContext.newInstance(TransactionRecords.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter recordsWriter = new StringWriter();
		transactionRecords.setTransactionRecord(transactionRecord);
		jaxbMarshaller.marshal(transactionRecords, recordsWriter);
		validatedrecords = recordsWriter.toString();
		}catch(JAXBException jaxbException){
			logger.error("Unable to produce the expected xml format");	
		}
		return validatedrecords;
	}
}
