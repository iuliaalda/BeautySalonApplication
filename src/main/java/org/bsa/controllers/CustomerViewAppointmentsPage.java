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
import java.text.SimpleDateFormat;
import java.util.Date;

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
                                                String[] app_date=a.getDate().split("-| ");
                                                Date d = new Date();
                                                String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(d);
                                                String[] date=modifiedDate.split("-");
                                                int d1,d2;
                                                d1=Integer.parseInt(app_date[2]);
                                                d2=Integer.parseInt(date[2]);
                                                if(app_date[1].equals(date[1])&&(d1-d2>2)){
                                                    AppointmentService.setStatustoFalse(a);
                                                    b.setText("Cancelled");
                                                    b.setDisable(true);
                                                }
                                                else{
                                                    Stage alerts = new Stage();
                                                    alerts.initModality(Modality.APPLICATION_MODAL);
                                                    VBox vb = new VBox(20);
                                                    Button closeB=new Button("OK!");
                                                    vb.setMinSize(200,100);
                                                    closeB.setOnAction(ee->alerts.close());
                                                    Label label=new Label("Cannot cancel an appointment 1 day prior to it!");
                                                    vb.getChildren().addAll(label,closeB);
                                                    vb.setAlignment(Pos.CENTER);
                                                    Scene scene = new Scene(vb);
                                                    alerts.setScene(scene);
                                                    alerts.showAndWait();

                                                }
                                                box.close();
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
    @FXML
    void handleGoBackToCart() {
        try{
            Stage stage = (Stage) goToMyCart.getScene().getWindow();
            Parent viewCartPageRoot = FXMLLoader.load(getClass().getResource("/Customer_Cart.fxml"));
            Scene cartScene=new Scene(viewCartPageRoot,600,380);
            stage.setScene(cartScene);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
