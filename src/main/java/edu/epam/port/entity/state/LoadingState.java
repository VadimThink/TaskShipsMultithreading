package edu.epam.port.entity.state;

import edu.epam.port.entity.Container;
import edu.epam.port.entity.Port;
import edu.epam.port.entity.Ship;
import edu.epam.port.exception.IncorrectOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LoadingState extends ShipState {
    private static final Logger logger = LogManager.getLogger(UnloadingState.class);
    private static LoadingState instance = new LoadingState();
    private static final Port port = Port.getInstance();
    private static final int TIME_NEEDED_FOR_LOAD_ONE_CONTAINER = 1;

    private LoadingState(){
    }

    public static LoadingState getInstance(){
        return instance;
    }

    @Override
    public void distribute(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship was distributed before!");
    }

    @Override
    public void unload(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship was unloaded before! (or not) But u can`t do this now, sorry");
    }

    @Override
    public void load(Ship ship) {
        for (int i = 0; i < ship.getContainerNumberShouldTake(); i++) {
            if(i < ship.getCapacity()) {
                Optional<Container> optional = port.getContainer();
                if (optional.isPresent()) {
                    Container container = optional.get();
                    ship.loadContainer(container);
                    logger.info("Container {} was moved from cargo warehouse to ship {}, cargo warehouse size is {}",
                            container.getId(), ship.getIdShip(), port.warehouseSize());
                    try {
                        TimeUnit.SECONDS.sleep(TIME_NEEDED_FOR_LOAD_ONE_CONTAINER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ship.setCurrentState(LeftPortState.getInstance());
    }

    @Override
    public void leave(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship cannot leave while loading!");
    }

}
