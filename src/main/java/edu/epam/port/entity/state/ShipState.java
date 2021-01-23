package edu.epam.port.entity.state;

import edu.epam.port.entity.Ship;
import edu.epam.port.exception.IncorrectOperationException;

public abstract class ShipState {

    public abstract void distribute(Ship ship) throws IncorrectOperationException;
    public abstract void unload(Ship ship) throws IncorrectOperationException;
    public abstract void load(Ship ship) throws IncorrectOperationException;
    public abstract void leave(Ship ship) throws IncorrectOperationException;

}
