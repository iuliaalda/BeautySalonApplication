package org.bsa.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.service.AppointmentService;
import org.bsa.service.ServicesService;
import sun.plugin2.jvm.RemoteJVMLauncher;

import java.io.IOException;

public class AppointmentsEmployeeController {
    @FXML
    TableView<Appointment> TodayAppsTableView;
    @FXML
    TableColumn<Appointment,String> todayDate;
    @FXML
    TableColumn<Appointment,String> todayService;
    public  void initialize() throws IOException {
        ServicesService.loadServices();
       AppointmentService.loadAppointments();
       initTable();
    }
    public  void initTable(){
        initCols();
        ObservableList<Appointment> aux= FXCollections.observableArrayList();
        aux=AppointmentService.returnCertainAppointment();
        TodayAppsTableView.setItems(aux);

    }
    public void initCols(){
        todayDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //todayService.setCellValueFactory(new PropertyValueFactory<>("service.getType()"));
        //todayService.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Appointment,String> ,
          //      public ObservableValue<String> call(CellDataFeatures<Appointment,String> p)

    }


    @FXML
    Button CancelledAppsButton;
    public void handleAppsCancelledAppsButton(ActionEvent actionEvent) {
        try{
            Scene goToCancelled;
            Stage stage = (Stage) CancelledAppsButton.getScene().getWindow();
            Parent viewCancelledAppointmentsListPageRoot = FXMLLoader.load(getClass().getResource("/CancelledAppointments.fxml"));
            goToCancelled= new Scene(viewCancelledAppointmentsListPageRoot,600,380);
            stage.setScene(goToCancelled);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    Button backfromAppsButton;
    public void gandleBacktoEmployeePage(ActionEvent actionEvent) {
        try{
            Scene goToEmployeePage;
            Stage stage = (Stage) backfromAppsButton.getScene().getWindow();
            Parent viewCancelledAppointmentsListPageRoot = FXMLLoader.load(getClass().getResource("/EmployeePage.fxml"));
            goToEmployeePage= new Scene(viewCancelledAppointmentsListPageRoot,600,380);
            stage.setScene(goToEmployeePage);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
