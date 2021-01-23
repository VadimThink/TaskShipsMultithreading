package edu.epam.port.reader;

import edu.epam.port.exception.FileReaderException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ShipFileReaderTest {

    @Test
    public void testReadFile() {
        ShipFileReader fileReader = new ShipFileReader();
        List<String> expected = new ArrayList<>();
        expected.add("100001, 50, 13, 200001 10; 200002 15; 200003 14; 200004 5");
        expected.add("100002, 30, 10, 200012 6; 200013 5; 200043 78; 200052 9");
        List<String> actual = new ArrayList<>();
        try {
            actual = fileReader.readFile("src\\main\\resources\\ships.txt");
        } catch (FileReaderException e) {
            e.printStackTrace();
        }
        assertEquals(actual, expected);
    }
}