package com.rabo.statementprocessorservice.service;

import com.rabo.statementprocessorservice.modal.TransactionRecord;
import java.util.List;

public interface StatementReader {
    public List<TransactionRecord> readRecords(String file);
}
