package org.bsa.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.model.Employee;
import org.bsa.service.FileSystemService;
import org.bsa.service.ServicesService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class EmployeeEditingControllerTest extends ApplicationTest{
    File sfile = new File("src\\test\\resources\\services.json");
    File efile = new File("src\\test\\resources\\employees.json");
    private EmployeeEditingController controller;
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
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ServicesService.loadServices(sfile);
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
        controller=new EmployeeEditingController();
        controller.nameField=new TextField();
        controller.priceField=new TextField();
        controller.nameField.setText(SERVICE_ADD);
        controller.priceField.setText(String.valueOf(PRICE_ADD));
    }

    @Test
    public void handleConfirmButton() throws IOException {
        //controller.handleConfirmButton();
        assertNotNull(controller.nameField.getText());
        //Window stage = controller.nameField.getScene().getWindow();
        //assertNull(controller.wrongPriceLabel.getText());

    }

    @Test
    public void getNewName() {
        assertNotNull(controller.nameField.getText());
    }

    @Test
    public void getNewPrice() {
        controller.getNewPrice();
        assertEquals(1000.0f,Float.parseFloat(controller.priceField.getText()),0.02);
    }
}