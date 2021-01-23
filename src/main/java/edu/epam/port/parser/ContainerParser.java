package edu.epam.port.parser;

import edu.epam.port.entity.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContainerParser {
    private static final String REGEX_SPLIT_CONTAINERS = "; ";
    private static final String REGEX_SPLIT_SINGLE_CONTAINER = " ";
    private static final int ID_POSITION = 0;
    private static final int WEIGHT_POSITION = 1;

    public List<Container> parseContainers(String line){
        List<String> splittedLine = Arrays.asList(line.split(REGEX_SPLIT_CONTAINERS));
        List<Container> containers = new ArrayList<>();
        for(int i = 0; i < splittedLine.size(); i++){
            Container container = parseContainer(splittedLine.get(i));
            containers.add(container);
        }
        return containers;
    }

    private Container parseContainer(String line){
        List<String> splittedLine = Arrays.asList(line.split(REGEX_SPLIT_SINGLE_CONTAINER));
        int id = Integer.parseInt(splittedLine.get(ID_POSITION));
        int weight = Integer.parseInt(splittedLine.get(WEIGHT_POSITION));
        return new Container(id, weight);
    }

}
