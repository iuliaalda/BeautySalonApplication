package org.bsa.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void getFirstName() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        String fn=e.getFirstName();
        assertEquals("fname",fn);
    }

    @Test
    public void setFirstName() {
        Employee e=new Employee();
        e.setFirstName("fname");
        assertEquals("fname",e.getFirstName());
    }

    @Test
    public void getLastName() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        String ln=e.getLastName();
        assertEquals("lname",ln);
    }

    @Test
    public void setLastName() {
        Employee e=new Employee();
        e.setLastName("lname");
        assertEquals("lname",e.getLastName());
    }

    @Test
    public void getAge() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        int age=e.getAge();
        assertEquals(30,age);
    }

    @Test
    public void setAge() {
        Employee e=new Employee();
        e.setAge(40);
        assertEquals(40,e.getAge());
    }

    @Test
    public void getServiceType() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        String serv=e.getServiceType();
        assertEquals("service",serv);
    }

    @Test
    public void setServiceType() {
        Employee e=new Employee();
        e.setServiceType("serv");
        assertEquals("serv",e.getServiceType());
    }

    @Test
    public void getYearsExperience() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        int age=e.getYearsExperience();
        assertEquals(5,age);
    }

    @Test
    public void setYearsExperience() {
        Employee e=new Employee();
        e.setYearsExperience(3);
        assertEquals(3,e.getYearsExperience());
    }

    @Test
    public void getListServices() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        ArrayList<Service> list=e.getListServices();
        assertEquals(s,list);
    }

    @Test
    public void setListServices() {
        Employee e=new Employee();
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        e.setListServices(s);
        assertEquals(s,e.getListServices());
    }

    @Test
    public void addtoServiceList() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        Service stest=new Service("Serv",20.0f,"testEmpl");
        e.addtoServiceList(stest);
        assertEquals(2,e.getListServices().size());
    }

    @Test
    public void removefromServiceList() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        Service stest=new Service("Serv",20.0f,"testEmpl");
        s.add(stest);
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        e.removefromServiceList(stest);
        assertEquals(1,e.getListServices().size());
    }

    @Test
    public void updateValue() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        Service stest=new Service("Serv",20.0f,"testEmpl");
        s.add(stest);
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        e.updateValue(stest,"serviceupdate",15.0f);
        assertEquals("serviceupdate",e.getListServices().get(0).getType());
    }

    @Test
    public void testToString() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e= new Employee("fname","lname",30,"service",5,s);
        String test="Employee{firstName='fname', lastName='lname', age=30, serviceType='service', yearsExperience=5, listServices=[Service{type='testServ', price=30.0, empl='testEmpl'}]}";
        assertEquals(test,e.toString());
    }

    @Test
    public void testEquals() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e1= new Employee("usr","pwd","Employee","fname","lname",30,"service",5,s);
        Employee e2= new Employee("usr","pwd","Employee","fname","lname",30,"service",5,s);
        assertEquals(true,e1.equals(e2));
    }

    @Test
    public void testHashCode() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Employee e1= new Employee("fname","lname",30,"service",5,s);
        Employee e2= new Employee("fname","lname",30,"service",5,s);
        assertEquals(true,e1.hashCode()==e2.hashCode());
    }
}