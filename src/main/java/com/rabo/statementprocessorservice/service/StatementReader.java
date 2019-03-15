package com.rabo.customerstatementprocessorservice.service;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import java.util.List;

public interface StatementReader {
    public List<TransactionRecord> readRecords(String file);
}
