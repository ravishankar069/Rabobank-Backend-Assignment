package com.rabo.customerstatementprocessorservice.modal;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Modal class for Parent node of xml statement holding the list of child nodes
 *
 * @param record
 */

@Data
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class TransactionRecords {
    @XmlElement(name = "record")
    private List<TransactionRecord> transactionRecord;
}
