package com.rabo.statementprocessorservice.modal;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Enum of specific file format
 *
 * @param xml
 * @param csv
 */
public enum FileFormat {
	xml,csv;
	
	public static boolean notify(MultipartFile file) throws IOException {
		String xml = FileFormat.xml.toString();
		String csv = FileFormat.csv.toString();
		String[] fileFormats = {xml,csv};
		boolean result = Arrays.stream(fileFormats).anyMatch((FilenameUtils.getExtension(file.getOriginalFilename()))::equals);
		if(result) {
			return true;	
		}else {
			return false;
		}
	}
}
