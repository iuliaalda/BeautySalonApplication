package org.bsa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ServicesServiceTest {
    File empfile = new File("src\\test\\resources\\employees.json");
    File sfile = new File("src\\test\\resources\\services.json");
    @BeforeClass
    public static void beforeClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        FileUtils.copyURLToFile(Service.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
       // FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        FileUtils.copyURLToFile(Service.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
       // FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
    }


    @Test
    public void addServices() {
    }

    @Test
    public void loadServices() throws IOException {
        EmployeeService.loadEmployees(empfile);
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.services);
        assertEquals(3,ServicesService.services.size());
    }

    @Test
    public void returnServ() throws IOException {
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.services);
        ObservableList<Service> services= FXCollections.observableArrayList();
        services=ServicesService.returnServ();
        assertNotNull(services);
        assertEquals(3,ServicesService.services.size());
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("services.json"), sfile);
    }

    @Test
    public void addService() throws IOException {
        Service s=new Service("Makeup1",55,"Iulia");
        ObservableList<Service> services= FXCollections.observableArrayList();
        services=ServicesService.returnServ();
        ServicesService.addService(s,sfile);
        assertNotNull(ServicesService.returnServ());
        //assertEquals(4,ServicesService.returnServ().size());
        assertEquals(4,ServicesService.services.size());
        //FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("services.json"), sfile);

    }

    @Test
    public void updateServ() throws IOException {
        ServicesService.loadServices(sfile);
        Service s=new Service("Simple Makeup",50,"Iulia");
        ObservableList<Service> s1=ServicesService.returnServ();
        ServicesService.updateServ(s,"Simple Makeup 1",50,sfile);
        ObservableList<Service> s2=ServicesService.returnServ();
        assertNotSame(s1,s2);
    }

    @Test
    public void removeServ() throws IOException {
        ServicesService.loadServices(sfile);
        Service s=new Service("Simple Makeup",50,"Iulia");
        ObservableList<Service> s1=ServicesService.returnServ();
        ServicesService.removeServ(s,sfile);
        assertEquals(2,ServicesService.services.size());

    }

    @Test
    public void setUsr() {
        ServicesService.setUsr("test_client");
        assertEquals("test_client",ServicesService.getUsr());
    }

    @Test
    public void getUsr() {
        ServicesService.setUsr("test_client");
        String user=ServicesService.getUsr();
        assertNotNull(user);
    }

    @Test
    public void returnCertainServ() throws IOException {
        ServicesService.loadServices(sfile);
       ServicesService.setUsr("Iulia");
        ObservableList<Service> service=ServicesService.returnCertainServ();
        ObservableList<Service> servicess=FXCollections.observableArrayList();
        for(Service s:service)
            if(s.getEmpl().equals("Iulia"))
                servicess.add(s);
            assertEquals(2,servicess.size());

    }
}