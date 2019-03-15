package com.rabo.statementprocessorservice.service;

import com.rabo.statementprocessorservice.modal.TransactionRecord;
import com.rabo.statementprocessorservice.utils.UniqueReferenceUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ValidatorService {
	@Autowired
	private UniqueReferenceUtility uniqueReferenceUtility;
	
    private Logger logger = LoggerFactory.getLogger(ValidatorService.class);

    public List<TransactionRecord> validateRecords(List<TransactionRecord> transactionRecordList) {        
        logger.info("Starts splitting invalid balance records and valid balance records");
        List<TransactionRecord> invalidBalanceRecords = transactionRecordList.stream()
                .filter(validRecords -> !(new BigDecimal(validRecords.getEndBalance())
                .equals(((new BigDecimal(validRecords.getStartBalance())).add(new BigDecimal(validRecords.getMutation()))))))
                .collect(Collectors.toList());
        for(TransactionRecord record : invalidBalanceRecords) {
        	record.setStatus("Balance validation failed");
        }
        logger.info("Starts read the duplicated records from the whole records");
        List<TransactionRecord> duplicatedreference = transactionRecordList.stream()
                .filter(uniqueReferenceUtility.distinctByReference(duplicateRecords -> ((TransactionRecord) duplicateRecords).getTransactionReference()).negate())
                .collect(Collectors.toList());
        System.out.println(duplicatedreference);
        for(TransactionRecord record : duplicatedreference) {
        	record.setStatus("Duplicated References Records");
        }
        invalidBalanceRecords.addAll(duplicatedreference);
        return invalidBalanceRecords;
    }
}
