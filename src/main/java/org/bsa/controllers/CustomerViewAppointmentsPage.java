package org.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bsa.model.Appointment;
import org.bsa.model.Service;
import org.bsa.service.AppointmentService;

import java.io.IOException;
import java.lang.management.BufferPoolMXBean;

public class CustomerViewAppointmentsPage {
    @FXML
    Button goToMyCart;
    @FXML
    Label text;
    @FXML
    TableView appsTableView;
    @FXML
    TableColumn servicesColumn;
    @FXML
    TableColumn dateColumn;
    @FXML
    TableColumn priceColumn;
    @FXML
    TableColumn cancelColumn;

    public void initialize() throws IOException {
        AppointmentService.loadAppointments();
        initTable();
    }
    public void initTable(){
        ObservableList<Appointment> app= FXCollections.observableArrayList();
        app=AppointmentService.returnMyAppointments();
        appsTableView.setItems(app);
        initCols();
    }

    private void initCols() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        //priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        servicesColumn.setCellValueFactory(new PropertyValueFactory<>("servicesList"));
        cancelColumn.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        Callback<TableColumn<Appointment,String>, TableCell<Appointment,String>> cellFactory =
                new Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>>() {
                    @Override
                    public TableCell<Appointment, String> call(final TableColumn<Appointment, String> param) {
                        final TableCell<Appointment,String> cell = new TableCell<Appointment,String>(){
                            final javafx.scene.control.Button b=new javafx.scene.control.Button("Cancel it");

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty){
                                    setGraphic(null);
                                    setText(null);
                                }
                                else{
                                        b.setOnAction((ActionEvent event1) -> {
                                            Stage box = new Stage();
                                            box.initModality(Modality.APPLICATION_MODAL);
                                            VBox alertscene = new VBox(20);
                                            alertscene.setMinSize(200,100);
                                            javafx.scene.control.Label aLabel=new javafx.scene.control.Label();
                                            aLabel.setText("Are you sure you want to cancel the appointment?");
                                            HBox hb=new HBox();
                                            hb.setSpacing(10);
                                            hb.setAlignment(Pos.CENTER);
                                            Button yesB=new Button("Yes");
                                            yesB.setOnAction(e->{
                                                Appointment a=getTableView().getItems().get(getIndex());
                                                AppointmentService.setStatustoFalse(a);
                                                box.close();
                                                b.setText("Cancelled");
                                                b.setDisable(true);
                                            });
                                            Button noB=new Button("No");
                                            noB.setOnAction(e->box.close());
                                            hb.getChildren().addAll(yesB,noB);
                                            alertscene.getChildren().addAll(aLabel,hb);
                                            alertscene.setAlignment(Pos.CENTER);
                                            Scene scene = new Scene(alertscene);
                                            box.setScene(scene);
                                            box.show();
                                    });
                                    setGraphic(b);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        cancelColumn.setCellFactory(cellFactory);
    }

    public void handleGoBackToCart(ActionEvent actionEvent) {



    }
}
