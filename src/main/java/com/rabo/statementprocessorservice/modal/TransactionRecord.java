package com.rabo.statementprocessorservice.modal;

import lombok.*;
import lombok.RequiredArgsConstructor;
import javax.xml.bind.annotation.*;

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
    @XmlAttribute(name = "reference")
    private String transactionReference;
    @XmlElement(name = "accountNumber")
    private String iban;
    @XmlElement(name = "startBalance")
    private String startBalance;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "endBalance")
    private String endBalance;
    @XmlElement(name = "mutation")
    private String mutation;
    @XmlElement(name = "status")
    private String status;
	
	@Override
	public String toString() {
		return "TransactionRecord [transactionReference=" + transactionReference + ", iban=" + iban + ", startBalance="
				+ startBalance + ", description=" + description + ", endBalance=" + endBalance + ", mutation="
				+ mutation + ", status=" + status + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionRecord other = (TransactionRecord) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endBalance == null) {
			if (other.endBalance != null)
				return false;
		} else if (!endBalance.equals(other.endBalance))
			return false;
		if (iban == null) {
			if (other.iban != null)
				return false;
		} else if (!iban.equals(other.iban))
			return false;
		if (mutation == null) {
			if (other.mutation != null)
				return false;
		} else if (!mutation.equals(other.mutation))
			return false;
		if (startBalance == null) {
			if (other.startBalance != null)
				return false;
		} else if (!startBalance.equals(other.startBalance))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transactionReference == null) {
			if (other.transactionReference != null)
				return false;
		} else if (!transactionReference.equals(other.transactionReference))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endBalance == null) ? 0 : endBalance.hashCode());
		result = prime * result + ((iban == null) ? 0 : iban.hashCode());
		result = prime * result + ((mutation == null) ? 0 : mutation.hashCode());
		result = prime * result + ((startBalance == null) ? 0 : startBalance.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transactionReference == null) ? 0 : transactionReference.hashCode());
		return result;
	}
	
}
