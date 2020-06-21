package org.bsa.controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        controller.appsTableView=new TableView();
        controller.dateColumn=new TableColumn();
        controller.priceColumn=new TableColumn();
        controller.servicesColumn=new TableColumn();
        controller.cancelColumn=new TableColumn();

    }
    @Test
    public void initialize() throws IOException {
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnMyAppointments());
        CustomerServicesListController s=new CustomerServicesListController();
        controller.initialize();
        assertEquals("[Appointment{date='2020-06-25 15:00', empl='Bia', client='B', status=true, services=[Service{type='Long Hairstyle', price=55.0, empl='Bia'}], servicesList='Long Hairstyle; '}, Appointment{date='2020-06-12 15:00', empl='Iulia', client='B', status=true, services=[Service{type='Bridal Makeup', price=75.0, empl='Iulia'}, Service{type='Simple Makeup', price=20.0, empl='Iulia'}], servicesList='Bridal Makeup; Simple Makeup; '}, Appointment{date='2020-06-26 11:00', empl='Iulia', client='B', status=true, services=[Service{type='Bridal Makeup', price=60.0, empl='Iulia'}], servicesList='Bridal Makeup; '}]",controller.appsTableView.getItems().toString());


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