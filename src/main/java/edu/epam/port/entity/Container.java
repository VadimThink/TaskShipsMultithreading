package edu.epam.port.entity;

public class Container {

    private int id;
    private double weight;

    public Container(int id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return id == container.id &&
                Double.compare(container.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        int result = id ^ (id >>> 32);
        result += 31 * weight;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Container{");
        sb.append("id=").append(id);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}
