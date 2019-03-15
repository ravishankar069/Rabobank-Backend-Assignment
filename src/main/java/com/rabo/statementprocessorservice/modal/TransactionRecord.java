package com.rabo.customerstatementprocessorservice.modal;

import lombok.*;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.*;

import com.opencsv.bean.CsvBindByName;

/**
 * Modal class holding the child node (record)
 *
 * @param reference
 * @param accountNumber
 * @param description
 * @param mutation
 * @param endBalance
 * @param startBalance
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "record")
@XmlSeeAlso({TransactionRecord.class})
public class TransactionRecord {
	@CsvBindByName(column = "reference")
    @XmlAttribute(name = "reference")
    private String transactionReference;
	@CsvBindByName(column = "accountNumber")
    @XmlElement(name = "accountNumber")
    private String iban;
	@CsvBindByName(column = "startBalance")
    @XmlElement(name = "startBalance")
    private String startBalance;
	@CsvBindByName(column = "description")
    @XmlElement(name = "description")
    private String description;
	@CsvBindByName(column = "endBalance")
    @XmlElement(name = "endBalance")
    private String endBalance;
	@CsvBindByName(column = "mutation")
    @XmlElement(name = "mutation")
    private String mutation;
	@CsvBindByName(column = "status")
    @XmlElement(name = "status")
    private String status;
	
	@Override
	public String toString() {
		return "TransactionRecord [transactionReference=" + transactionReference + ", iban=" + iban + ", startBalance="
				+ startBalance + ", description=" + description + ", endBalance=" + endBalance + ", mutation="
				+ mutation + ", status=" + status + "]";
	}
	
}
