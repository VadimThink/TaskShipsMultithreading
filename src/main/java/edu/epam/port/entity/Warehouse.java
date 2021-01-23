package edu.epam.port.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse {

    private static final Logger logger = LogManager.getLogger(Warehouse.class);
    private static final int CAPACITY = 100;
    private static final int TOP_CONTAINER = 0;
    private static Warehouse instance = new Warehouse();
    private final List<Container> containers = new ArrayList<>();

    public static Warehouse getInstance() {
        return instance;
    }

    public Optional<Container> getContainer() {
        Optional<Container> container = Optional.ofNullable(containers.remove(TOP_CONTAINER));
        return container;
    }

    public void addContainer(Container container) {
        if (containers.size() < CAPACITY) {
            containers.add(container);
        } else {
            logger.warn("Unable to add container, warehouse is full!");
        }
    }

    public int getContainerNumber() {
        return containers.size();
    }

}
