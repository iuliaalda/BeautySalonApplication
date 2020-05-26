package org.bsa.model;

import java.util.ArrayList;
import java.util.Objects;

public class Appointment {
    String date;
    String empl;
    ArrayList<Service> services;

    public Appointment(String date, String empl, ArrayList<Service> services) {
        this.date = date;
        this.empl = empl;
        this.services = services;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmpl() {
        return empl;
    }

    public void setEmpl(String empl) {
        this.empl = empl;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getEmpl(), that.getEmpl()) &&
                Objects.equals(getServices(), that.getServices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getEmpl(), getServices());
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date='" + date + '\'' +
                ", empl='" + empl + '\'' +
                ", services=" + services +
                '}';
    }
}
