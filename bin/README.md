Rabobank Customer Statement Processor
--------------------------------------
This assignment's main objective is that rabobank receives monthly deliveries of customer statement records and those information is delivered in two formats, CSV and XML, so that these records need to be validated.

Requirements
------------
• all transaction references should be unique
• the end balance needs to be validated

Abstract
--------
First of all, the records has been read from the Xml or Csv file and each customer records has been stored in the indexes of the string array to check the duplicate reference number and bigdecimal class has been used to validate the start balance and mutation with end balance.

Precautions
-----------
Pojo has been used for secure and appropriate usage of customer records and annotations concept is used to wire the readed record and to do the validation.

XmlReader
---------
Jaxb been used to read the xml file and appropriate list of record has been returned to do the validations.

CsvReader
---------
Usually its a good practice to use libraries in the code because it reduces the code length hence here used OpenCsv library.

Validations
------------
> Bigdecimal has been used to do the balance validations.
> To remove the duplicates filter with distinctwithreference has been used.
