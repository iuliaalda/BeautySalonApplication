package org.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class CustomerServicesListControllerTest {
    File efile = new File("src\\test\\resources\\employees.json");
    File sfile = new File("src\\test\\resources\\services.json");
    private CustomerServicesListController controller;
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
        controller=new CustomerServicesListController();
        controller.custServTable=new TableView<>();
        controller.nameColumn=new TableColumn();
        controller.priceColumn=new TableColumn();
        controller.checkboxColumn=new TableColumn();

    }

    @Test
    public void initialize() throws Exception{
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        controller.initialize();
        assertEquals("[Service{type='Simple Makeup', price=50.0, empl='Iulia'}, Service{type='Bridal Makeup', price=60.0, empl='Iulia'}, Service{type='Long Hairstyle', price=55.0, empl='Bia'}]",controller.custServTable.getItems().toString());
    }

    @Test
    public void initTable() throws Exception{
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
    }

    @Test
    public void initCols() throws Exception{
        ServicesService.loadServices(sfile);
        assertNotNull(ServicesService.returnServ());
        controller.initCols();
        assertEquals(0,controller.custServTable.getItems().size());
    }

    @Test
    public void handleBackButton() {
    }

    @Test
    public void handleDoneButton() {
    }

    @Test
    public void getSelected() {
       //assertNull(controller.getSelected());
    }

}