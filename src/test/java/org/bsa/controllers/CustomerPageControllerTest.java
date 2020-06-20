package org.bsa.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.model.Employee;
import org.bsa.service.FileSystemService;
import org.bsa.service.ServicesService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;

import java.io.File;

import static org.junit.Assert.*;

public class CustomerPageControllerTest extends ApplicationTest{
    private CustomerPageController controller;
    @Override
    public void start(Stage stage) throws Exception {
        Scene listScene;
        stage = (Stage) controller.welcomeCustomerLabel.getScene().getWindow();
        Parent viewStaffListPageRoot = FXMLLoader.load(getClass().getResource("/customerStaffList.fxml"));
        listScene= new Scene(viewStaffListPageRoot,600,380);
        stage.setScene(listScene);
    }
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ApplicationTest.launch(Main.class);
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        controller=new CustomerPageController();
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
    }

    @Test
    public void handleViewStaffButton() throws Exception{
        //controller.handleViewStaffButton();
        //assertTrue();
        //FxAssert.verifyThat(, WindowMatchers.isShowing());
    }

    @Test
    public void handleViewServices() {
    }

    @Test
    public void handleCartAction() {
    }

    @Test
    public void handleAppointmentAction() {
    }
}