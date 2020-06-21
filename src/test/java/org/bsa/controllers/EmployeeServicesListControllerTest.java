package org.bsa.controllers;

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

public class EmployeeServicesListControllerTest extends ApplicationTest{
    File sfile = new File("src\\test\\resources\\services.json");
    File efile = new File("src\\test\\resources\\employees.json");
    private EmployeeServicesListController controller;
    public static final String SERVICE_ADD = "serviceTest";
    public static final float PRICE_ADD = 1000.0f;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ServicesService.setUsr("Iulia");
        ApplicationTest.launch(Main.class);
    }

    @Before
    public void setUp() throws Exception  {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ServicesService.loadServices(sfile);
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
        controller=new EmployeeServicesListController();
        controller.serviceAddText=new TextField();
        controller.priceAddText=new TextField();
        controller.serviceAddText.setText(SERVICE_ADD);
        controller.priceAddText.setText(String.valueOf(PRICE_ADD));
        controller.sfile=sfile;
        controller.efile=efile;
    }

    @Test
    public void handleAddAction() throws IllegalStateException, Exception{
        //ServicesService.setUsr("Iulia");
        ServicesService.loadServices(sfile);
        EmployeeService.loadEmployees(efile);
        controller.handleAddAction();
        assertEquals(3, ServicesService.returnServ().size());
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
    }
    @Test(expected = IllegalStateException.class)
    public void handleAddActionTwo() throws IllegalStateException, Exception{
        //ServicesService.setUsr("Iulia");
        ServicesService.loadServices(sfile);
        EmployeeService.loadEmployees(efile);
        controller.priceAddText.setText("NOT_A_NUMBER");
        controller.handleAddAction();

        assertEquals(3, ServicesService.returnServ().size());
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
    }

    @Test
    public void initialize() throws Exception{
        EmployeeService.loadEmployees(efile);
        ServicesService.loadServices(sfile);
        assertNotNull(EmployeeService.returnEmp());
        assertNotNull(ServicesService.returnServ());
    }

    @Test
    public void handleBackButton() {
    }
}