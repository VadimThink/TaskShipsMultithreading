package edu.epam.port.entity;

import java.util.Optional;

public class Pier {
    private int id;
    private Ship ship;

    public Pier(int id) {
        this.id = id;
    }

    public int getPierId() {
        return id;
    }

    public Optional<Ship> getShip() {
        return Optional.ofNullable(ship);
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void removeShip() {
        ship = null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ship != null ? ship.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pier pier = (Pier) o;
        return id == pier.id &&
                ship.equals(pier.ship);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pier{");
        sb.append("id=").append(id);
        sb.append(", ship=").append(ship);
        sb.append('}');
        return sb.toString();
    }
}
