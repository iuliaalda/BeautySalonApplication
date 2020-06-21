package org.bsa.controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.service.AppointmentService;
import org.bsa.service.FileSystemService;
import org.bsa.service.ServicesService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AppointmentsEmployeeControllerTest extends ApplicationTest{

    File afile = new File("src\\test\\resources\\appointments.json");
    File sfile = new File("src\\test\\resources\\services.json");
    private AppointmentsEmployeeController controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ServicesService.setUsr("Bia");
        AppointmentService.setUsr("Bia");
        ApplicationTest.launch(Main.class);
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ServicesService.loadServices(sfile);
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
        FileUtils.copyURLToFile(Appointment.class.getClassLoader().getResource("appointments.json"),new File("src/test/resources/appointments.json"));
        controller=new AppointmentsEmployeeController();
       controller.sfile=sfile;
       controller.afile=afile;
       controller.TodayAppsTableView=new TableView<>();
       controller.todayDate=new TableColumn<>();
       controller.todayService=new TableColumn();
    }
    @Test
    public void initialize() throws IOException {
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnTodayApps());
        CustomerServicesListController s=new CustomerServicesListController();
        controller.initialize();
        assertEquals("[]",controller.TodayAppsTableView.getItems().toString());
    }

    @Test
    public void initTable() throws IOException {
        ServicesService.loadServices(sfile);
        AppointmentService.loadAppointments(afile);
        AppointmentService.returnTodayApps();
        assertNotNull(ServicesService.returnServ());
        assertNotNull(AppointmentService.returnTodayApps());
    }



    @Test
    public void initCols() throws IOException {
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnTodayApps());
        CustomerServicesListController s=new CustomerServicesListController();
        controller.initCols();
        assertEquals(0,controller.TodayAppsTableView.getItems().size());
    }

    @Test
    public void handleAppsCancelledAppsButton() {
    }

    @Test
    public void gandleBacktoEmployeePage() {
    }
}