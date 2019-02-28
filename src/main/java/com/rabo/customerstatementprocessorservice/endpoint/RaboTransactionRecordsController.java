package com.rabo.customerstatementprocessorservice.endpoint;

import com.rabo.customerstatementprocessorservice.modal.TransactionRecord;
import com.rabo.customerstatementprocessorservice.service.FileExtensionHandlerService;
import com.rabo.customerstatementprocessorservice.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * RaboTransactionRecordsController enables the user to posting the customer statement to service
 *
 * @author Ravisankar Rajamanickam
 */

@RestController
public class RaboTransactionRecordsController {
    private FileExtensionHandlerService fileExtensionHandlerService;
    private ValidatorService validatorService;

    @Autowired
    RaboTransactionRecordsController(ValidatorService validatorService,
                                     FileExtensionHandlerService fileExtensionHandlerService) {
        this.validatorService = validatorService;
        this.fileExtensionHandlerService = fileExtensionHandlerService;
    }

    @PostMapping("/rabo/customerstatement")
    public List<TransactionRecord> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return validatorService.validateRecords(fileExtensionHandlerService
                .handleFileExtension(file));
    }
}