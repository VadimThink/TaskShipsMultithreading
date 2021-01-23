package edu.epam.port.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private static final int PIER_NUMBER = 5;
    private static final Logger logger = LogManager.getLogger(Port.class);
    private static Port instance = new Port();
    private final Semaphore semaphore = new Semaphore(PIER_NUMBER, true);
    private final Lock lock = new ReentrantLock();
    private final Queue<Pier> freePiers = new LinkedList<>();
    private final List<Pier> usedPiers = new ArrayList<>();
    private static Warehouse warehouse = Warehouse.getInstance();

    private Port() {
        for (int i = 1; i <= PIER_NUMBER; i++) {
            freePiers.add(new Pier(i));
        }
    }
    public static Port getInstance() {
        return instance;
    }

    public Optional<Container> getContainer() {
        lock.lock();
        Optional<Container> optional = warehouse.getContainer();
        lock.unlock();
        return optional;
    }

    public void addContainer(Container container) {
        lock.lock();
        warehouse.addContainer(container);
        lock.unlock();
    }

    public int warehouseSize() {
        return warehouse.getContainerNumber();
    }

    public void requestPier(Ship ship) {
        try {
            logger.info(freePiers.size() + " piers available. Ship № "+ ship.getIdShip() +" is waiting to distribute, "
                    + semaphore.getQueueLength() + "ships in queue");
            semaphore.acquire();
            Pier pier = freePiers.poll();
            usedPiers.add(pier);
            pier.setShip(ship);
            ship.setPierId(pier.getPierId());
            logger.info("Ship № {} got a pier № {}, {} piers available, {} ships in queue",
                    ship.getId(), pier.getPierId(), freePiers.size(), semaphore.getQueueLength());
        } catch (InterruptedException e) {
            logger.error("Thread is canceled");
        }
    }

    public void leavePier(int pierId) {
        Optional<Pier> optionalPier = Optional.empty();
        for(int i = 0; i < usedPiers.size(); i++){
            Pier currentPier = usedPiers.get(i);
            if(currentPier.getPierId() == pierId){
                optionalPier = Optional.of(currentPier);
            }
        }
        if (optionalPier.isPresent()) {
            Pier pier = optionalPier.get();
            Optional<Ship> optionalShip = pier.getShip();
            if (optionalShip.isPresent()) {
                Ship ship = optionalShip.get();
                int shipId = ship.getIdShip();
                pier.removeShip();
                ship.setPierIdToUnknown();
                if (usedPiers.remove(pier)) {
                    freePiers.offer(pier);
                    logger.info("Ship {} left pier number {}", shipId, pier.getPierId());
                    semaphore.release();
                }
            }
        }
    }
}
