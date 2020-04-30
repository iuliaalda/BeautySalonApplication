package org.bsa.controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.bsa.service.UserService;
import javafx.scene.control.TextField;

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
    UserService usr;

    @FXML
    public void initialize() {
        userChoiceBox.getItems().addAll("Employee", "Customer");
        userChoiceBox.setValue("Customer");

    }

    public void handleLoginAction() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

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

        //log-in as customer
        if(userChoiceBox.getValue()=="Customer")
        {

            //go to customer page
            Scene customerScene;
        }
        //log-in as employee
        if(userChoiceBox.getValue()=="Employee")
        {
            //go to employee page
            Scene employeeScene;
        }
        //invalid login
        warningLogin.setText("Invalid login!");
    }
}



