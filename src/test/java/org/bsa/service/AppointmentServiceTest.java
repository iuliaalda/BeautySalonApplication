package org.bsa.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class AppointmentServiceTest {
    File sfile = new File("src\\test\\resources\\services.json");
    File afile = new File("src\\test\\resources\\appointments.json");

    @BeforeClass
    public static void beforeClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("appointments.json"),new File("src/test/resources/appointments.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("appointments.json"),new File("src/test/resources/appointments.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
    }

    @Test
    public void writeAppointment() {
    }

    @Test
    public void loadAppointments() throws Exception{
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.appointments);
        assertEquals(10,AppointmentService.appointments.size());
    }

    @Test
    public void returnAppointments() throws Exception{
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.appointments);
        ObservableList<Appointment> apps=FXCollections.observableArrayList();
        apps=AppointmentService.returnAppointments();
        assertNotNull(apps);
        assertEquals(10,apps.size());
    }

    @Test
    public void returnTodayApps() throws  Exception{
        AppointmentService.loadAppointments(afile);
        String user="Bia";
        AppointmentService.setUsr(user);
        Date d = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(d);
        ObservableList<Appointment> test_apps=AppointmentService.returnTodayApps();
        for(Appointment aux:test_apps){
            assertEquals(aux.getDate(),modifiedDate);
            assertEquals(true,aux.getStatus());
        }
    }

    @Test
    public void returnNextCancelledApps() throws Exception{
        AppointmentService.loadAppointments(afile);
        String user="Iulia";
        AppointmentService.setUsr(user);
        Date d = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(d);
        ObservableList<Appointment> test_apps=AppointmentService.returnNextCancelledApps();
        for(Appointment aux:test_apps){
            //assertEquals(aux.getDate(),modifiedDate);
            assertEquals(false,aux.getStatus());
        }
    }

    @Test
    public void returnCertainAppointment() throws Exception{

    }

    @Test
    public void returnMyAppointments() throws Exception{
        AppointmentService.loadAppointments(afile);
        String client="A";
        AppointmentService.setClientusr(client);
        ObservableList<Appointment> test_apps=AppointmentService.returnMyAppointments();
        for(Appointment aux:test_apps){
            assertEquals(aux.getClient(),client);
        }
    }

    @Test
    public void returnCancelledAppointment() {
    }

    @Test
    public void setStatustoFalse() throws Exception{
        AppointmentService.loadAppointments(afile);
        ArrayList<Service> s=new ArrayList<>();
        Service s1=new Service("Simple Makeup",50.0f,"Iulia");
        s.add(s1);
        Appointment atest=new Appointment(true,"2020-07-01 12:00","Iulia","A",s);
        AppointmentService.setStatustoFalse(atest,afile);
        ObservableList<Appointment> list=AppointmentService.returnAppointments();
        for(Appointment aux:list){
            if(aux.getServices().equals(s)){
                assertEquals(false,aux.getStatus());
            }
        }
    }

    @Test
    public void getUsr() {
        AppointmentService.setUsr("test_user");
        String user=AppointmentService.getUsr();
        assertNotNull(user);
    }

    @Test
    public void setUsr() {
        AppointmentService.setUsr("test_user");
        assertEquals("test_user",AppointmentService.getUsr());
    }

    @Test
    public void getClientusr() {
        AppointmentService.setUsr("test_client");
        String user=AppointmentService.getUsr();
        assertNotNull(user);
    }

    @Test
    public void setClientusr() {
        AppointmentService.setClientusr("test_client");
        assertEquals("test_client",AppointmentService.getClientusr());
    }

    @Test
    public void removeCancelled() throws Exception{
        AppointmentService.loadAppointments(afile);
        ArrayList<Service> s=new ArrayList<>();
        Service s1=new Service("Bridal Makeup",75.0f,"Iulia");
        Service s2=new Service("Simple Makeup",20.0f,"Iulia");
        s.add(s1);
        s.add(s2);
        Appointment atest=new Appointment(false,"2020-06-21 14:00","Iulia","A",s);
        AppointmentService.removeCancelled(atest,afile);
        ObservableList<Appointment> list=AppointmentService.returnAppointments();
        assertEquals(9,list.size());
    }

    @Test
    public void addAppointment() throws Exception{
        ArrayList<Service> s=new ArrayList<>();
        ArrayList<Service> ss=new ArrayList<>();
        Service s1=new Service("Bridal Makeup",75.0f,"Iulia");
        Service s2=new Service("Long Hairstyle",55.0f,"Bia");
        s.add(s1);
        ss.add(s2);
        Appointment atest1=new Appointment(true,"2020-07-12 10:00","Iulia","B",s);
        Appointment atest2=new Appointment(true,"2020-07-14 15:00","Bia","A",ss);
        ArrayList<Appointment> list=new ArrayList<>();
        list.add(atest1);
        list.add(atest2);
        AppointmentService.addAppointment(list,afile);
        ObservableList<Appointment> apps=AppointmentService.returnAppointments();
        assertEquals(11,AppointmentService.appointments.size());
    }
}