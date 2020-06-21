package org.bsa.controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.exceptions.InvalidRole;
import org.bsa.exceptions.LoginFail;
import org.bsa.model.Employee;
import org.bsa.model.User;
import org.bsa.service.FileSystemService;
import org.bsa.service.ServicesService;
import org.bsa.service.UserService;
import org.bsa.service.UserServiceTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class LoginControllerTest extends ApplicationTest {
    File lfile = new File("src\\test\\resources\\users.json");
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private LoginController controller;
    @BeforeClass
    public static void beforeClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();

        ApplicationTest.launch(Main.class);
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile(lfile);
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        controller=new LoginController();
        controller.usernameTextField = new TextField();
        controller.passwordTextField = new TextField();
        controller.userChoiceBox = new ChoiceBox();
        controller.warningLogin= new Label();
        controller.passwordTextField.setText(TEST_PASSWORD);
        controller.usernameTextField.setText(TEST_USER);

    }

    @Test
    public void initialize() throws IOException {
       UserService.loadUsersFromFile(lfile);
        controller.userChoiceBox.getItems().addAll("Employee", "Customer");

    }

    @Test
    public void handleLoginAction() throws IOException, InvalidRole, LoginFail {
        UserService.loadUsersFromFile(lfile);
        controller.usernameTextField.setText("Iulia");
        controller.passwordTextField.setText("abcde");

        assertNotNull(controller);
    }
    @Test(expected =InvalidRole.class)
    public void handleLoginActionWithWrongRole() throws IOException, LoginFail, InvalidRole {
        UserService.loadUsersFromFile(lfile);

       // assertNotNull(UserService.users);
       UserService.checkLoginCredentials("Iulia","abcde","Customer");

    }
    @Test(expected =LoginFail.class)
    public void handleLoginAction2() throws IOException, InvalidRole, LoginFail {
        UserService.loadUsersFromFile(lfile);
        controller.usernameTextField.setText("");
        controller.passwordTextField.setText("abcde");
        //controller.userChoiceBox.setValue("Customer");
        assertNotNull(controller);
        UserService.checkLoginCredentials("","abcde","Customer");

    }
    @Test
    public void handleLogin() throws IOException {
        UserService.loadUsersFromFile(lfile);
        controller.usernameTextField.setText("");
        controller.warningLogin.setText("Please enter your username!");
        assertNotNull(controller.warningLogin.getText());
        assertEquals(controller.warningLogin.getText(),"Please enter your username!");
    }
    @Test(expected = LoginFail.class)
    public void testLoginFail() throws Exception, InvalidRole {
        String usr="Iulia";
        String password="";
        UserService.checkLoginCredentials(usr,password,"customer");

    }
    @Test
    public void handleLogin1() throws IOException {
        UserService.loadUsersFromFile(lfile);
        controller.usernameTextField.setText("Iulia");
        controller.passwordTextField.setText("");
        controller.warningLogin.setText("Please enter your password!");
        assertEquals(controller.warningLogin.getText(),"Please enter your password!");
    }
}