package org.bsa.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getUsername() {
        User u=new User("abc","xyz","Employee");
        String username=u.getUsername();
        assertEquals("abc",username);
    }

    @Test
    public void setUsername() {
        User u=new User();
        u.setUsername("user");
        assertEquals("user",u.getUsername());
    }

    @Test
    public void getPassword() {
        User u=new User("abc","xyz","Employee");
        String pwd=u.getPassword();
        assertEquals("xyz",pwd);
    }

    @Test
    public void setPassword() {
        User u=new User();
        u.setPassword("passwd");
        assertEquals("passwd",u.getPassword());
    }

    @Test
    public void getRole() {
        User u=new User("abc","xyz","Employee");
        String role=u.getRole();
        assertEquals("Employee",role);
    }

    @Test
    public void setRole() {
        User u=new User();
        u.setRole("Customer");
        assertEquals("Customer",u.getRole());
    }

    @Test
    public void testEquals() {
        assertEquals(true,new User("Ana","abcd","Employee").equals(new User("Ana","abcd","Employee")));
    }

    @Test
    public void testHashCode() {
        User u1= new User("Ana","abcd","Employee");
        User u2=new User("Ana","abcd","Employee");
        assertEquals(true,u1.hashCode()==u2.hashCode());
    }

    @Test
    public void testToString() {
        User u=new User("Ana","abcd","Employee");
        String ustring="User{username='Ana', password='abcd', role='Employee'}";
        assertEquals(ustring,u.toString());
    }
}