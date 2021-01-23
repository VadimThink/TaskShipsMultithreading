package edu.epam.port.entity.state;

import edu.epam.port.entity.Container;
import edu.epam.port.entity.Port;
import edu.epam.port.entity.Ship;
import edu.epam.port.exception.IncorrectOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class UnloadingState extends ShipState {
    private static final Logger logger = LogManager.getLogger(UnloadingState.class);
    private static UnloadingState instance = new UnloadingState();
    private static final Port port = Port.getInstance();
    private static final int TIME_NEEDED_FOR_UNLOAD_ONE_CONTAINER = 1;
    private static final int TOP_CONTAINER = 0;

    private UnloadingState(){
    }

    public static UnloadingState getInstance(){
        return instance;
    }

    @Override
    public void distribute(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship was distributed before!");
    }

    @Override
    public void unload(Ship ship) {
        while (ship.getContainerNumber() != 0) {
            Optional<Container> optionalContainer = ship.unloadContainer(TOP_CONTAINER);
            if (optionalContainer.isPresent()) {
                Container container = optionalContainer.get();
                port.addContainer(container);
                logger.info("Container {} was moved from ship {} to warehouse, warehouse size is {}",
                        container.getId(), ship.getIdShip(), port.warehouseSize());
                try {
                    TimeUnit.SECONDS.sleep(TIME_NEEDED_FOR_UNLOAD_ONE_CONTAINER);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ship.setCurrentState(LoadingState.getInstance());
    }

    @Override
    public void load(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship cannot load while unload!");
    }

    @Override
    public void leave(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship cannot leave while unloading!");
    }
}
