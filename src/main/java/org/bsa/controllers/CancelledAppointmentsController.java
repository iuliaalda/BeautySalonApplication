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
import org.bsa.service.AppointmentService;
import org.bsa.service.ServicesService;

import java.io.IOException;


public class CancelledAppointmentsController {


    @FXML
    Button backtoAppts;

    public void handleToAppointmentsFromCancelled(ActionEvent actionEvent) {
        try {
            Scene goToAvailable;
            Stage stage = (Stage) backtoAppts.getScene().getWindow();
            Parent viewCancelledAppointmentsListPageRoot = FXMLLoader.load(getClass().getResource("/Employee_appointments.fxml"));
            goToAvailable = new Scene(viewCancelledAppointmentsListPageRoot, 600, 380);
            stage.setScene(goToAvailable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    TableView<Appointment> CancelledAppsTableView;
    @FXML
    TableColumn date;
    @FXML
    TableColumn service;
    @FXML
    TableColumn freeTime=new TableColumn<>("Free Time");;

    public void initialize() throws IOException {
        ServicesService.loadServices();
        AppointmentService.loadAppointments();
        initTable();
    }

    public void initTable() {
        initCols();
        ObservableList<Appointment> aux = FXCollections.observableArrayList();
        aux = AppointmentService.returnCancelledAppointment();
        CancelledAppsTableView.setItems(aux);

    }

    public void initCols(){
        // AppointmentService as=new AppointmentService();
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        service.setResizable(true);
        service.setCellValueFactory(new PropertyValueFactory<>("servicesList"));
        freeTime.setCellValueFactory(new PropertyValueFactory<>("free"));
        Callback<TableColumn<Appointment,String>, TableCell<Appointment,String>> cellFactory =
                new Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>>() {
                    @Override
                    public TableCell<Appointment, String> call(final TableColumn<Appointment, String> param) {
                        final TableCell<Appointment,String> cell = new TableCell<Appointment,String>() {
                            Boolean buttonPressed = false;
                            final Button button = new Button("FreeTime");
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    //System.out.println("pressed button......");
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    setGraphic(button);
                                    button.setOnAction((ActionEvent event) -> {
                                        if(!buttonPressed)
                                            buttonPressed=true;
                                        Appointment a = getTableView().getItems().get(getIndex());
                                            AppointmentService.removeAppsfromCancelled(a);
                                            button.setDisable(true);

                                        // System.out.println("pressed button");
                                    });
                                }
                            }
                        };

                        return cell;
                    }
                };
        freeTime.setCellFactory(cellFactory);
        //CancelledAppsTableView.getColumns().add(freeTime);
    }
}



