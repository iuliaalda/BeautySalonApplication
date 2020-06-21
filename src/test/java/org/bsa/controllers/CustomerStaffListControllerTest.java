package org.bsa.controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.model.Employee;
import org.bsa.service.EmployeeService;
import org.bsa.service.FileSystemService;
import org.bsa.service.ServicesService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;

import static org.junit.Assert.*;

public class CustomerStaffListControllerTest {
    File efile = new File("src\\test\\resources\\employees.json");
    private CustomerPageController controllerpage;
    private CustomerStaffListController controller;
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
        controller=new CustomerStaffListController();
        controller.tableStaff=new TableView<>();
        controller.fName=new TableColumn<>();
        controller.lName=new TableColumn<>();
        controller.age=new TableColumn<>();
        controller.service=new TableColumn<>();
        controller.yrsExp=new TableColumn<>();
    }
    @Test
    public void initialize() throws Exception{
        EmployeeService.loadEmployees(efile);
        assertNotNull(EmployeeService.returnEmp());
        controller.initialize();
        assertEquals("[Employee{firstName='Iulia', lastName='Alda', age=20, serviceType='makeup', yearsExperience=2, listServices=[Service{type='Simple Makeup', price=50.0, empl='Iulia'}, Service{type='Bridal Makeup', price=60.0, empl='Iulia'}]}, Employee{firstName='Bianka', lastName='Beuka', age=21, serviceType='hair stylist', yearsExperience=3, listServices=[Service{type='Long Hairstyle', price=55.0, empl='Bia'}]}]",controller.tableStaff.getItems().toString());
    }

    @Test
    public void handleBackButton() {
        /*controller.tableStaff=new TableView<>();
        controller.handleBackButton();
        controllerpage=new CustomerPageController();
        assertNotNull(controllerpage.welcomeCustomerLabel.getText());*/
    }
}