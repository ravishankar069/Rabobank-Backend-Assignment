package com.rabo.statementprocessorservice.service;

import java.util.List;

import com.rabo.statementprocessorservice.modal.TransactionRecord;

public interface ResponseWriter {
	public String writeRecords(List<TransactionRecord> transacctionRecord);
}
