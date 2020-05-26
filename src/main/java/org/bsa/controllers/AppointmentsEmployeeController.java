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
        todayService= new TableColumn<>("Service");
        //todayService.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Appointment,String> ,
          //      public ObservableValue<String> call(CellDataFeatures<Appointment,String> p)

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
    @FXML
    Button CancelledAppsButton;
    public void handleAppsCancelledAppsButton(ActionEvent actionEvent) {
        try{
            Scene goAhead;
            Stage stage = (Stage) CancelledAppsButton.getScene().getWindow();
            Parent viewCancelledAppointmentsListPageRoot = FXMLLoader.load(getClass().getResource("/CancelledAppointments.fxml"));
            goAhead= new Scene(viewCancelledAppointmentsListPageRoot,600,380);
            stage.setScene(goAhead);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
