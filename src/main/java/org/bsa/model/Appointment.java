package org.bsa.model;

import java.util.ArrayList;
import java.util.Objects;

public class Appointment {
    String date;
    String empl;
    String client;
    Boolean status;
    ArrayList<Service> services;
    String servicesList;

    public Appointment(){}

    public Appointment(Boolean status,String date, String empl, String client, ArrayList<Service> services) {
        this.date = date;
        this.empl = empl;
        this.client = client;
        this.status = status;
        this.services = services;
        for(Service s:services) {
            servicesList = getServicesList();
        }
    }


    public  String getServicesList(){
        servicesList="";
        for(Service s:services) {
            servicesList = servicesList+ s.getType()+"; ";
        }
        return servicesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return date.equals(that.date) &&
                empl.equals(that.empl) &&
                client.equals(that.client) &&
                status.equals(that.status) &&
                services.equals(that.services) &&
                servicesList.equals(that.servicesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, empl, client, status, services, servicesList);
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setServicesList(String servicesList) {
        this.servicesList = servicesList;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "date='" + date + '\'' +
                ", empl='" + empl + '\'' +
                ", client='" + client + '\'' +
                ", status=" + status +
                ", services=" + services +
                ", servicesList='" + servicesList + '\'' +
                '}';
    }
}