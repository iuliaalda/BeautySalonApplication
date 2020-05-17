package org.bsa.model;

import javafx.scene.control.Button;

import java.util.Objects;

public class Service {
    private String type;
    private float price;
    Button editButton;
    Button deleteButton;

    public Service(){}
    public Service(String type, float price) {
        this.type = type;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Float.compare(service.price, price) == 0 &&
                type.equals(service.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, price);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
