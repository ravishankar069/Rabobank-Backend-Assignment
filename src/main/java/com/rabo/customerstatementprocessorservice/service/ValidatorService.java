package com.rabo.customerstatementprocessorservice.service;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ValidatorService {
    private Logger logger = LoggerFactory.getLogger(ValidatorService.class);

    private static <T> Predicate<T> distinctByReference(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public List<TransactionRecord> validateRecords(List<TransactionRecord> transactionRecordList) {
        logger.info("Starts validating the end balance of records");
        List<TransactionRecord> validateBalance = transactionRecordList.stream()
                .filter(validRecords -> new BigDecimal(validRecords.getEndBalance())
                .equals(((new BigDecimal(validRecords.getStartBalance())).add(new BigDecimal(validRecords.getMutation())))))
                .collect(Collectors.toList());
        logger.info("Starts removing the duplicate records based on reference");
        return validateBalance.stream()
                .filter(distinctByReference(uniqueRecords -> uniqueRecords.getTransactionReference()))
                .collect(Collectors.toList());
    }
}
