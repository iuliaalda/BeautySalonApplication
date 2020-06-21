package org.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
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

import static org.junit.Assert.*;

public class CustomerCartControllerTest extends ApplicationTest{
    File sfile = new File("src\\test\\resources\\services.json");
    File afile = new File("src\\test\\resources\\appointments.json");
    private CustomerCartController controller;
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
        controller=new CustomerCartController();
        controller.hour=new ChoiceBox();
        controller.tableCart=new TableView<>();
        controller.deleteColumn=new TableColumn();
        controller.serviceColumn=new TableColumn();
        controller.priceColumn=new TableColumn();
        //controller.hour.getItems().addAll("first","second");

    }

    @Test
    public void initializeChoiceBox() {
        controller.initializeChoiceBox();
        assertEquals(11,controller.hour.getItems().size());
    }

    @Test
    public void initialize() throws Exception{
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnAppointments());
        CustomerServicesListController s=new CustomerServicesListController();
        controller.initialize();
        assertEquals("[]",controller.tableCart.getItems().toString());
    }

    @Test
    public void initCols() throws Exception{
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        AppointmentService.loadAppointments(afile);
        assertNotNull(AppointmentService.returnAppointments());
        controller.initCols();
        assertEquals(0,controller.tableCart.getItems().size());
    }

    @Test
    public void setSelectedservices() {
        ObservableList<Service> testserv=FXCollections.observableArrayList();
        Service s1=new Service("testServ",30.0f,"testEmpl");
        Service s2=new Service("testServ2",90.0f,"testEmpl2");
        testserv.add(s1);
        testserv.add(s2);
        controller.setSelectedservices(testserv);
        assertEquals(testserv,controller.selectedservices);
    }

    @Test
    public void handleBackButton() {
    }

    @Test
    public void handleFinishButton() {
    }

    @Test
    public void handleGoToMyApps() {
    }
}