package org.bsa.model;

import java.util.ArrayList;
import java.util.Objects;

public class Employee extends User{
    private String firstName;
    private String lastName;
    private int age;
    private String serviceType;
    private int yearsExperience;
    private ArrayList<Service> listServices;
    public Employee(){super();}

    public Employee(String username, String password,  String firstName, String lastName, int age, String serviceType, int yearsExperience, ArrayList<Service> listServices) {
        super(username, password, "Employee");
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.serviceType = serviceType;
        this.yearsExperience = yearsExperience;
        this.listServices=listServices;
    }

    public Employee(String firstName, String lastName, int age, String serviceType, int yearsExperience, ArrayList<Service> listServices) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.serviceType = serviceType;
        this.yearsExperience = yearsExperience;
        this.listServices=listServices;
    }

    public Employee(String username, String password, String role, String firstName, String lastName, int age, String serviceType, int yearsExperience, ArrayList<Service> listServices) {
        super(username, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.serviceType = serviceType;
        this.yearsExperience = yearsExperience;
        this.listServices=listServices;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public ArrayList<Service> getListServices() { return listServices; }

    public void setListServices(ArrayList<Service> listServices) { this.listServices = listServices; }

    public void addtoServiceList(Service s){
        listServices.add(s);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", serviceType='" + serviceType + '\'' +
                ", yearsExperience=" + yearsExperience +
                ", listServices=" + listServices +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                yearsExperience == employee.yearsExperience &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                serviceType.equals(employee.serviceType) &&
                listServices.equals(employee.listServices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, age, serviceType, yearsExperience, listServices);
    }
}
