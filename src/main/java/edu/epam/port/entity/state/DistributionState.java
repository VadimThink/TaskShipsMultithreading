package edu.epam.port.entity.state;

import edu.epam.port.entity.Port;
import edu.epam.port.entity.Ship;
import edu.epam.port.exception.IncorrectOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class DistributionState extends ShipState {
    private static final Logger logger = LogManager.getLogger(DistributionState.class);
    private static DistributionState instance = new DistributionState();
    private static final Port port = Port.getInstance();
    private static final int TIME_NEEDED_FOR_ARRIVE_TO_PIER = 3;

    private DistributionState() {
    }

    public static DistributionState getInstance() {
        return instance;
    }

    @Override
    public void distribute(Ship ship) {
        port.requestPier(ship);
        try {
            TimeUnit.SECONDS.sleep(TIME_NEEDED_FOR_ARRIVE_TO_PIER);
        } catch (InterruptedException e) {
            logger.error("This thread is dead and never come back");
        }
        ship.setCurrentState(UnloadingState.getInstance());
    }

    @Override
    public void unload(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship is not in port yet!");
    }

    @Override
    public void load(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship is not in port yet!");
    }

    @Override
    public void leave(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship is not in port yet!");
    }
}
