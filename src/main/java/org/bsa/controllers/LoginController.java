package org.bsa.controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.bsa.exceptions.InvalidRole;
import org.bsa.exceptions.LoginFail;
import org.bsa.model.User;
import org.bsa.service.AppointmentService;
import org.bsa.service.EmployeeService;
import org.bsa.service.ServicesService;
import org.bsa.service.UserService;
import javafx.scene.control.TextField;

import java.io.IOException;

import static org.bsa.service.UserService.checkLoginCredentials;

//import javax.jws.soap.SOAPBinding;
//import javax.xml.soap.Text;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ChoiceBox userChoiceBox;
    @FXML
    private TextField text;
    @FXML
    private Label warningLogin;
    Stage stage;
    private String u;
    //private UserService U;

    @FXML
    public void initialize() throws IOException{
        //EmployeeService.writeEmployees();
        //ServicesService.addServices();
        userChoiceBox.getItems().addAll("Employee", "Customer");
        userChoiceBox.setValue("Customer");

    }


    public void handleLoginAction() throws LoginFail, IOException, InvalidRole {
        User usr= new User();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String role = (String) userChoiceBox.getValue();
        usr.setUsername(username);
        usr.setPassword(password);
        usr.setRole(role);
        //missing username
        if(username==null || username.isEmpty())
        {
            warningLogin.setText("Please enter your username!");
            return;
        }

        //missing password
        if(password==null || password.isEmpty())
        {
            warningLogin.setText("Please enter your password!");
            return;
        }

        //check if correct credentials
        try {
            checkLoginCredentials(usr.getUsername(), usr.getPassword(),usr.getRole());
            //log-in as customer
            if(role=="Customer")
            {
                //go to customer page
                AppointmentService as=new AppointmentService();
                as.setClientusr(usr.getUsername());
                try{
                    Stage stage = (Stage) warningLogin.getScene().getWindow();
                    Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerPage.fxml"));
                    Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
                    stage.setScene(customerScene);
                } catch (IOException e){
                    e.printStackTrace();
                }
                return;
            }

            //log-in as employee
            if(role=="Employee")
            {
                //go to employee page
                ServicesService ss=new ServicesService();
                AppointmentService aps=new AppointmentService();
                aps.setUsr(usr.getUsername());
                ss.setUsr(usr.getUsername());
                try{
                    Stage stage = (Stage) warningLogin.getScene().getWindow();
                    Parent viewEmployeePageRoot = FXMLLoader.load(getClass().getResource("/EmployeePage.fxml"));
                    Scene employeeScene=new Scene(viewEmployeePageRoot,600,380);
                    stage.setScene(employeeScene);
                } catch (IOException e){
                    e.printStackTrace();
                }
                return;
            }
        }catch (LoginFail e){
            warningLogin.setText("Invalid login! Wrong credentials!");
        }catch (InvalidRole e){
            warningLogin.setText("Invalid login! Wrong role selected!");
        }

    }
}