package org.bsa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AppointmentsEmployeeController {
    @FXML
    Button backfromAppsButton;
    public void handleBackButton(ActionEvent actionEvent) {
        try{
            Scene goBack;
            Stage stage = (Stage) backfromAppsButton.getScene().getWindow();
            Parent viewAppointmentsListPageRoot = FXMLLoader.load(getClass().getResource("/EmployeePage.fxml"));
            goBack= new Scene(viewAppointmentsListPageRoot,600,380);
            stage.setScene(goBack);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
