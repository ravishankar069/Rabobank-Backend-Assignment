package com.rabo.customerstatementprocessorservice.modal;

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
}
