package org.bsa.model;

import javafx.scene.control.Button;

import java.util.Objects;

public class Service {
    private String type;
    private float price;
    private String empl;

    public Service(){}
    public Service(String type, float price,String empl) {
        this.type = type;
        this.price = price;
        this.empl=empl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getEmpl() {
        return empl;
    }

    public void setEmpl(String empl) {
        this.empl = empl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Float.compare(service.price, price) == 0 &&
                type.equals(service.type) &&
                empl.equals(service.empl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, price, empl);
    }

    @Override
    public String toString() {
        return "Service{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", empl='" + empl + '\'' +
                '}';
    }
}
