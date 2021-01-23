package edu.epam.port.entity;

import edu.epam.port.exception.IncorrectOperationException;
import edu.epam.port.entity.state.DistributionState;
import edu.epam.port.entity.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Ship extends Thread {

    private static final Logger logger = LogManager.getLogger(Ship.class);
    private static final int UNKNOWN_PIER_ID = -1;

    private int idShip;
    private ShipState shipState;
    private List<Container> containers;
    private int capacity;
    private int pierId;
    private int containerNumberShouldTake;

    public Ship(int shipId, int capacity, int containerNumberShouldTake, List<Container> containers) {
        this.idShip = shipId;
        this.capacity = capacity;
        this.containerNumberShouldTake = containerNumberShouldTake;
        this.containers = containers;
        pierId = UNKNOWN_PIER_ID;
        shipState = DistributionState.getInstance();
    }

    public int getIdShip() {
        return idShip;
    }

    public void setIdShip(int id) {
        this.idShip = id;
    }

    public ShipState getCurrentState() {
        return shipState;
    }

    public void setCurrentState(ShipState state) {
        this.shipState = state;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPierId() {
        return pierId;
    }

    public void setPierId(int pierId) {
        this.pierId = pierId;
    }

    public void setPierIdToUnknown(){
        this.pierId = UNKNOWN_PIER_ID;
    }

    public int getContainerNumber(){
        return containers.size();
    }

    public void setContainerNumberShouldTake(int containerNumberShouldTake){
        this.containerNumberShouldTake = containerNumberShouldTake;
    }

    public int getContainerNumberShouldTake(){
        return containerNumberShouldTake;
    }

    public void loadContainer(Container container) {
        if ((containers.size() < capacity)) {
            containers.add(container);
        } else {
            logger.error("Unable to load container, it's more than ship capacity!");
        }
    }

    public Optional<Container> unloadContainer(int idContainer) {
        Container container = containers.remove(idContainer);
        return Optional.ofNullable(container);
    }

    @Override
    public void run() {
        try {
            shipState.distribute(this);
            shipState.unload(this);
            shipState.load(this);
            shipState.leave(this);
        }catch (IncorrectOperationException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public int hashCode() {
        int result = idShip;
        result = 31 * result + capacity;
        result = 31 * result + shipState.hashCode();
        result = 31 * result + pierId;
        result = 31 * result + containerNumberShouldTake;
        result = 31 * result + containers.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return idShip == ship.idShip &&
                capacity == ship.capacity &&
                pierId == ship.pierId &&
                containerNumberShouldTake == ship.containerNumberShouldTake &&
                shipState.equals(ship.shipState) &&
                containers.equals(ship.containers);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("idShip=").append(idShip);
        sb.append(", shipState=").append(shipState);
        sb.append(", containers=").append(containers);
        sb.append(", capacity=").append(capacity);
        sb.append(", containerNumberShouldTake=").append(containerNumberShouldTake);
        sb.append(", pierId=").append(pierId);
        sb.append('}');
        return sb.toString();
    }
}
