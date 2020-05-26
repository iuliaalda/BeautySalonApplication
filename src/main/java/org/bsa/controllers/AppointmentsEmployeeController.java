package org.bsa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.service.AppointmentService;
import org.bsa.service.ServicesService;

import java.io.IOException;

public class AppointmentsEmployeeController {
    @FXML
    TableView<Appointment> TodayAppsTableView;
    @FXML
    TableColumn<Appointment,String> todayDate;
    @FXML
    TableColumn<Appointment,String> todayService;
    public  void initialize() throws IOException {
        initTable();
        ServicesService.loadServices();
        AppointmentService.loadAppointments();
    }
    public void initTable()
    {

    }
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
