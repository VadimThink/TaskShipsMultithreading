package edu.epam.port.reader;

import edu.epam.port.exception.FileReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShipFileReader {
    private static final Logger logger = LogManager.getLogger(ShipFileReader.class);

    public List<String> readFile(String fileName) throws FileReaderException {
        BufferedReader fileReader = null;
        List<String> stringList;
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            stringList = fileReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Exception: " + e.toString());
            throw new FileReaderException("Something wrong with file input.", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        logger.info("End of reading data");
        return stringList;
    }
}
