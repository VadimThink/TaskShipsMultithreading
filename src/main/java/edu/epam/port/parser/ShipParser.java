package edu.epam.port.parser;

import edu.epam.port.entity.Container;
import edu.epam.port.entity.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipParser {
    private static final String REGEX_SPLIT_LINE = ", ";
    private static final int ID_POSITION = 0;
    private static final int CAPACITY_POSITION = 1;
    private static final int CONTAINER_NUMBER_SHOULD_TAKE_POSITION = 2;
    private static final int CONTAINER_LIST_START_POSITION = 3;

    /*
    Прошу не судить строго, делал проект весь день и доделываю ночью и знаю, что парсер можно
    сделать намного лучше с помощью паттернов, но спать хочется...
     */

    public List<Ship> parseShips(List<String> data){
        List<Ship> ships = new ArrayList<>();
        for(int i = 0; i < data.size(); i++) {
            List<String> splittedLine = Arrays.asList(data.get(i).split(REGEX_SPLIT_LINE).clone());
            ships.add(parseShip(splittedLine));
        }
        return ships;
    }
    
    private Ship parseShip(List<String> splittedLine){
        ContainerParser containerParser = new ContainerParser();
        int id = Integer.parseInt(splittedLine.get(ID_POSITION));
        int capacity = Integer.parseInt(splittedLine.get(CAPACITY_POSITION));
        int containerNumberShouldTake = Integer.parseInt(splittedLine.get(CONTAINER_NUMBER_SHOULD_TAKE_POSITION));
        List<Container> containers= containerParser.parseContainers(splittedLine.get(CONTAINER_LIST_START_POSITION));;
        Ship newShip = new Ship(id, capacity, containerNumberShouldTake, containers);
        return newShip;
    }
    
}
