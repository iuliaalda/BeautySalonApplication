package org.bsa.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void getType() {
        Service s=new Service("testServ",30.0f,"testEmpl");
        String type=s.getType();
        assertEquals("testServ",type);
    }

    @Test
    public void setType() {
        Service s= new Service();
        s.setType("service");
        assertEquals("service",s.getType());
    }

    @Test
    public void getPrice() {
        Service s=new Service("testServ",30.0f,"testEmpl");
        float price=s.getPrice();
        assertEquals(30.0f,price,0.02);
    }

    @Test
    public void setPrice() {
        Service s= new Service();
        s.setPrice(10.0f);
        assertEquals(10.0f,s.getPrice(),0.02);
    }

    @Test
    public void getEmpl() {
        Service s=new Service("testServ",30.0f,"testEmpl");
        String employee=s.getEmpl();
        assertEquals("testEmpl",employee);
    }

    @Test
    public void setEmpl() {
        Service s= new Service();
        s.setEmpl("employee");
        assertEquals("employee",s.getEmpl());
    }

    @Test
    public void testEquals() {
        assertEquals(true,new Service("testServ",30.0f,"testEmpl").equals(new Service("testServ",30.0f,"testEmpl")));
    }

    @Test
    public void testHashCode() {
        Service s1=new Service("testServ",30.0f,"testEmpl");
        Service s2=new Service("testServ",30.0f,"testEmpl");
        assertEquals(true,s1.hashCode()==s2.hashCode());
    }

    @Test
    public void testToString() {
        Service s=new Service("testServ",30.0f,"testEmpl");
        String test="Service{type='testServ', price=30.0, empl='testEmpl'}";
        assertEquals(test,s.toString());
    }
}