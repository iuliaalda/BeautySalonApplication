package org.bsa.controllers;

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
    }
    @Test
    public void initialize() throws Exception{
        EmployeeService.loadEmployees(efile);
        assertNotNull(EmployeeService.returnEmp());
    }

    @Test
    public void handleBackButton() {
        /*controller.tableStaff=new TableView<>();
        controller.handleBackButton();
        controllerpage=new CustomerPageController();
        assertNotNull(controllerpage.welcomeCustomerLabel.getText());*/
    }
}