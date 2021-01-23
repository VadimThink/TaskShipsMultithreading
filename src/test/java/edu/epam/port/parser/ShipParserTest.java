package edu.epam.port.parser;

import edu.epam.port.entity.Container;
import edu.epam.port.entity.Ship;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ShipParserTest {

    @Test
    public void testParseShips() {
        List<Ship> expected = new ArrayList<>();
        List<Container> listOne = new ArrayList<>();
        List<Container> listTwo = new ArrayList<>();
        listOne.add(new Container(200001, 10));
        listOne.add(new Container(200002, 15));
        listOne.add(new Container(200003, 14));
        listOne.add(new Container(200004, 5));
        listTwo.add(new Container(200012, 6));
        listTwo.add(new Container(200013, 5));
        listTwo.add(new Container(200043, 78));
        listTwo.add(new Container(200052, 9));
        expected.add(new Ship(100001, 50, 13, listOne));
        expected.add(new Ship(100002, 30, 10, listTwo));
        String first = "100001, 50, 13, 200001 10; 200002 15; 200003 14; 200004 5";
        String two = "100002, 30, 10, 200012 6; 200013 5; 200043 78; 200052 9";
        List<String> stringList = new ArrayList<>();
        stringList.add(first);
        stringList.add(two);
        ShipParser parser = new ShipParser();
        List<Ship> actual = parser.parseShips(stringList);
        assertEquals(actual, expected);
    }
}