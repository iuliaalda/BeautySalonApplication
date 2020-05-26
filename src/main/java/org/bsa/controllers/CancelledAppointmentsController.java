package org.bsa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.sql.BatchUpdateException;


public class CancelledAppointmentsController {
    @FXML
    Button backtoAppts;
    public void handleToAppointmentsFromCancelled(ActionEvent actionEvent) {
        try{
            Scene goToAvailable;
            Stage stage = (Stage) backtoAppts.getScene().getWindow();
            Parent viewCancelledAppointmentsListPageRoot = FXMLLoader.load(getClass().getResource("/Employee_appointments.fxml"));
            goToAvailable= new Scene(viewCancelledAppointmentsListPageRoot,600,380);
            stage.setScene(goToAvailable);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
