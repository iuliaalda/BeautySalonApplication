package org.bsa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeePageController {

    @FXML
    Label viewApptsEmployee;
    public void handleEmployeeButton(){
        try{
            Scene listScene;
            Stage stage = (Stage) viewApptsEmployee.getScene().getWindow();
            Parent viewStaffListPageRoot = FXMLLoader.load(getClass().getResource("/employeeServicesList.fxml"));
            listScene= new Scene(viewStaffListPageRoot,600,380);
            stage.setScene(listScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleAppointmentButton(){
        try{
            Scene listScene;
            Stage stage = (Stage) viewApptsEmployee.getScene().getWindow();
            Parent viewAppointmentsListPageRoot = FXMLLoader.load(getClass().getResource("/Employee_appointments.fxml"));
            listScene= new Scene(viewAppointmentsListPageRoot,600,380);
            stage.setScene(listScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
