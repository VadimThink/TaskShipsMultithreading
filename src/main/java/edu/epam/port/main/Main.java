package edu.epam.port.main;

import edu.epam.port.entity.Container;
import edu.epam.port.entity.Ship;
import edu.epam.port.entity.Warehouse;
import edu.epam.port.exception.FileReaderException;
import edu.epam.port.parser.ContainerParser;
import edu.epam.port.parser.ShipParser;
import edu.epam.port.reader.ShipFileReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String SHIPS_FILEPATH = "src\\main\\resources\\ships.txt";
    private static final String CONTAINERS_FILEPATH = "src\\main\\resources\\containers.txt";
    private static final int FIRST_LINE_IN_LIST_INDEX = 0;

    /*
    Мейн очень кривой, создан только для демонстрации работоспособности
     */

    public static void main(String[] args){
        ShipFileReader reader = new ShipFileReader();
        ShipParser shipParser = new ShipParser();
        List<Ship> ships = new ArrayList<>();
        ContainerParser containerParser = new ContainerParser();
        List<Container> containers = new ArrayList<>();
        try {
            containers = containerParser.parseContainers(reader.readFile(CONTAINERS_FILEPATH).get(FIRST_LINE_IN_LIST_INDEX));
        } catch (FileReaderException e) {
            e.printStackTrace();
        }
        Warehouse warehouse = Warehouse.getInstance();
        for (Container container: containers) {
            warehouse.addContainer(container);
        }
        try {
            ships = shipParser.parseShips(reader.readFile(SHIPS_FILEPATH));
        } catch (FileReaderException e) {
            e.printStackTrace();
        }
        for (Ship ship : ships) {
            ship.start();
        }
    }

}
