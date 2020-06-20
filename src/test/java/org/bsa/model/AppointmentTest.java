package org.bsa.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AppointmentTest {

    @Test
    public void getServicesList() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        String list=a.getServicesList();
        assertEquals("testServ; ",list);
    }

    @Test
    public void testEquals() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a1 = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        Appointment a2 = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        assertEquals(true,a1.equals(a2));
    }

    @Test
    public void testHashCode() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a1 = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        Appointment a2 = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        assertEquals(true,a1.hashCode()==a2.hashCode());
    }

    @Test
    public void getClient() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        String cl=a.getClient();
        assertEquals("cl",cl);
    }

    @Test
    public void setClient() {
        Appointment a=new Appointment();
        a.setClient("cl");
        assertEquals("cl",a.getClient());
    }

    @Test
    public void setServicesList() {
        /*ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a=new Appointment();
        a.setServicesList("testServ");
        assertEquals("testServ; ",a.getServicesList());*/
    }

    @Test
    public void getDate() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        String date=a.getDate();
        assertEquals("2020-07-23 14:00",date);
    }

    @Test
    public void setDate() {
        Appointment a=new Appointment();
        a.setDate("2020-07-23 14:00");
        assertEquals("2020-07-23 14:00",a.getDate());
    }

    @Test
    public void getEmpl() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        String emp=a.getEmpl();
        assertEquals("empl",emp);
    }

    @Test
    public void setEmpl() {
        Appointment a=new Appointment();
        a.setEmpl("emp");
        assertEquals("emp",a.getEmpl());
    }

    @Test
    public void getStatus() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        boolean st=a.getStatus();
        assertEquals(true,st);
    }

    @Test
    public void setStatus() {
        Appointment a=new Appointment();
        a.setStatus(true);
        assertEquals(true,a.getStatus());
    }

    @Test
    public void getServices() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        ArrayList<Service> l=a.getServices();
        assertEquals(s,l);
    }

    @Test
    public void setServices() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a=new Appointment();
        a.setServices(s);
        assertEquals(s,a.getServices());
    }

    @Test
    public void testToString() {
        ArrayList<Service> s=new ArrayList<>();
        Service srv=new Service("testServ",30.0f,"testEmpl");
        s.add(srv);
        Appointment a = new Appointment(true,"2020-07-23 14:00","empl","cl",s);
        assertEquals("Appointment{date='2020-07-23 14:00', empl='empl', client='cl', status=true, services=[Service{type='testServ', price=30.0, empl='testEmpl'}], servicesList='testServ; '}",a.toString());
    }
}