package com.rabo.customerstatementprocessorservice.utils;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import java.io.File;
import java.util.List;

public interface StatementReader {
    public List<TransactionRecord> readRecords(String file);
}
