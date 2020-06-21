package org.bsa.controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
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

public class CancelledAppointmentsControllerTest extends ApplicationTest {
    File sfile = new File("src\\test\\resources\\services.json");
    File afile = new File("src\\test\\resources\\appointments.json");
    private CancelledAppointmentsController controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ApplicationTest.launch(Main.class);
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        controller=new CancelledAppointmentsController();
        controller.CancelledAppsTableView=new TableView<>();
        controller.date=new TableColumn();
        controller.freeTime=new TableColumn();
        controller.service=new TableColumn();
        //controller.hour.getItems().addAll("first","second");

    }

    @Test
    public void handleToAppointmentsFromCancelled() {
    }

    @Test
    public void initialize() throws IOException {
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnNextCancelledApps());
        CustomerServicesListController s=new CustomerServicesListController();
        controller.initialize();
        assertEquals("[]",controller.CancelledAppsTableView.getItems().toString());
    }

    @Test
    public void initTable() throws IOException {
        ServicesService.loadServices(sfile);
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnNextCancelledApps());

    }

    @Test
    public void initCols() throws IOException {
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnNextCancelledApps());
        controller.initCols();
        assertEquals(0,controller.CancelledAppsTableView.getItems().size());
    }
}