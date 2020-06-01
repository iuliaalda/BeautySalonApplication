package org.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bsa.model.Appointment;
import org.bsa.model.Service;
import org.bsa.service.AppointmentService;
import org.bsa.service.ServicesService;

import java.awt.*;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.sql.BatchUpdateException;


public class CancelledAppointmentsController {
    @FXML
    TableView<Appointment> CancelledAppsTableView;
    @FXML
    TableColumn<Appointment,String> date;
    @FXML
    TableColumn<Appointment,String> service;
    @FXML
    TableColumn freeTime;
    public  void initialize() throws IOException {
        ServicesService.loadServices();
        AppointmentService.loadAppointments();
        initTable();
    }
    public void initTable(){
        initCols();
        ObservableList<Appointment> aux= FXCollections.observableArrayList();
        aux=AppointmentService.returnCancelledAppointment();
        CancelledAppsTableView.setItems(aux);

    }
    public  void initCols() {
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        service.setResizable(true);
        service.setCellValueFactory(new PropertyValueFactory<>("servicesList"));

    }
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
