package edu.epam.port.entity.state;

import edu.epam.port.entity.Port;
import edu.epam.port.entity.Ship;
import edu.epam.port.exception.IncorrectOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LeftPortState extends ShipState {
    private static final Logger logger = LogManager.getLogger(LeftPortState.class);
    private static final LeftPortState instance = new LeftPortState();
    private static final Port port = Port.getInstance();
    private static final int TIME_NEEDED_FOR_LEAVE = 3;

    public static LeftPortState getInstance(){
        return instance;
    }

    @Override
    public void distribute(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("Ship leaving now! Do u want to return it? I`m not, so sorry");
    }

    @Override
    public void unload(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("U cannot unload ship while it`s leaving");
    }

    @Override
    public void load(Ship ship) throws IncorrectOperationException {
        throw new IncorrectOperationException("U cannot load ship while it`s leaving");
    }

    @Override
    public void leave(Ship ship) {
        logger.info("Ship {} is leaving pier № {}", ship.getIdShip(), ship.getPierId());
        try {
            TimeUnit.SECONDS.sleep(TIME_NEEDED_FOR_LEAVE);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        logger.info("Ship {} has left pier number {}!", ship.getIdShip(), ship.getPierId());
        int pierId = ship.getPierId();
        port.leavePier(pierId);
    }
}
