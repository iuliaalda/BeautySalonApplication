package org.bsa.controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bsa.service.UserService;

import javax.jws.soap.SOAPBinding;
import javax.xml.soap.Text;

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
    private Text text;
    Stage stage;

    @FXML
    public void initialize() {
        userChoiceBox.getItems().addAll("Employee", "Customer");
        userChoiceBox.setValue("Customer");

    }

    public void handleLoginAction() {
        try {
            UserService.checkLoginCredentials(usernameTextField.getText(), passwordTextField.getText());
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Logn.fxml"));
            stage = new Stage();
            stage.setTitle("FXML Welcome");
            stage.setScene(new Scene(root, 600, 400));
            stage.setFullScreen(false);
            stage.setResizable(false);
            UserService.loadUsersFromFile();
            stage.show();
            text.setTextContent("Login successfully");
        } catch (Exception e) {
            text.setTextContent(e.getMessage());
        }

    }
}



