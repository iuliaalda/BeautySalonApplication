package org.bsa.controllers;

import javafx.scene.control.TextField;
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

public class AppointmentsEmployeeControllerTest {

    File afile = new File("src\\test\\resources\\appointments.json");
    File sfile = new File("src\\test\\resources\\services.json");
    private AppointmentsEmployeeController controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ServicesService.setUsr("Bia");
        AppointmentService.setUsr("Bia");
        //ApplicationTest.launch(Main.class);
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ServicesService.loadServices(sfile);
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
        FileUtils.copyURLToFile(Appointment.class.getClassLoader().getResource("appointments.json"),new File("src/test/resources/appointments.json"));
        /*controller=new AppointmentsEmployeeController();
       controller.sfile=sfile;
       controller.afile=afile;
       controller.TodayAppsTableView.setItems(AppointmentService.returnTodayApps());*/
    }
    @Test
    public void initialize() throws IOException {
        AppointmentService.loadAppointments(afile);
        AppointmentService.returnTodayApps();

    }

    @Test
    public void initTable() throws IOException {
        AppointmentService.loadAppointments(afile);
        AppointmentService.returnTodayApps();
        assertEquals(0,AppointmentService.returnTodayApps().size());
        //controller.TodayAppsTableView.setItems(AppointmentService.returnTodayApps());
       // assertEquals(0,AppointmentService.returnTodayApps().size());
    }

    @Test
    public void initCols() {
    }

    @Test
    public void handleAppsCancelledAppsButton() {
    }

    @Test
    public void gandleBacktoEmployeePage() {
    }
}