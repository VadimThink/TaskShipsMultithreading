package edu.epam.port.parser;

import edu.epam.port.entity.Container;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ContainerParserTest {

    @Test
    public void testParseContainers() {
        List<Container> expected = new ArrayList<>();
        expected.add(new Container(100, 5));
        expected.add(new Container(101, 6));
        expected.add(new Container(102, 7));
        String testString = "100 5; 101 6; 102 7";
        ContainerParser parser = new ContainerParser();
        List<Container> actual = parser.parseContainers(testString);
        assertEquals(actual, expected);
    }
}