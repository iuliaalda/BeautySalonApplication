package org.bsa.controllers;

import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.service.AppointmentService;
import org.bsa.service.EmployeeService;
import org.bsa.service.FileSystemService;
import org.bsa.service.ServicesService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class CustomerViewAppointmentsPageTest extends  ApplicationTest {
    File afile = new File("src\\test\\resources\\appointments.json");
    File sfile = new File("src\\test\\resources\\services.json");
    private CustomerViewAppointmentsPage controller;
    public static final String TEXT = "My Appointments";
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        AppointmentService.setUsr("Iulia");
        AppointmentService.setClientusr("B");
        ApplicationTest.launch(Main.class);
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ServicesService.loadServices(afile);
        FileUtils.copyURLToFile(Appointment.class.getClassLoader().getResource("appointments.json"),new File("src/test/resources/appointments.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
        controller=new CustomerViewAppointmentsPage();

    }
    @Test
    public void initialize() throws IOException {
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnTodayApps());
        CustomerServicesListController s=new CustomerServicesListController();
        controller.initialize();
        //assertEquals("[]",controller.appsTableView.getItems().toString());


    }



    @Test
    public void initTable() throws IOException {
        ServicesService.loadServices(sfile);
        AppointmentService.loadAppointments(afile);
        AppointmentService.returnMyAppointments();
        assertNotNull(ServicesService.returnServ());
        assertNotNull(AppointmentService.returnMyAppointments());
    }

    @Test
    public void handleGoBackToCart() throws IOException {
        ServicesService.loadServices(sfile);
    }
}