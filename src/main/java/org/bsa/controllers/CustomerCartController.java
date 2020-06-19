package org.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DateTimeStringConverter;
import org.bsa.exceptions.EqualHour;
import org.bsa.exceptions.InvalidHour;
import org.bsa.model.Appointment;
import org.bsa.model.Service;
import org.bsa.service.AppointmentService;
import org.bsa.service.EmployeeService;
import org.jetbrains.annotations.Nls;
import org.omg.CORBA.DATA_CONVERSION;
import sun.security.krb5.internal.APOptions;

import javax.jws.HandlerChain;
import javax.swing.*;
import javax.swing.text.TabableView;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CustomerCartController {
    @FXML
    TableView<Service> tableCart;
    @FXML
    TableColumn serviceColumn;
    @FXML
    TableColumn priceColumn;
    @FXML
    TableColumn deleteColumn;
    @FXML
    Button goToMyApps;
    @FXML
    Button backButton;
    @FXML
    ChoiceBox hour = new ChoiceBox();
    @FXML
    Label display;
    @FXML
    Button finishButton;
    @FXML
    DatePicker datePicker = new DatePicker();
    @FXML
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");

    @FXML
    public void initializeChoiceBox() {
        hour.getItems().addAll("8:00", "9:00","10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00");
        hour.setValue("8:00");
    }

    static ObservableList<Service> selectedservices = FXCollections.observableArrayList();
    public void initialize() throws IOException {
       /* format = new SimpleDateFormat("yyyy:MM:dd HH:mm");
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate date=datePicker.getValue();
                System.out.print(date);
                display.setText(date.toString());
            }
        });*/
        AppointmentService.loadAppointments();
        initializeChoiceBox();
        CustomerServicesListController s = new CustomerServicesListController();
        ObservableList<Service> services = FXCollections.observableArrayList();
        services = s.getSelected();
        tableCart.setItems(services);
        // System.out.print(services+"\n ");
        initCols();

    }

    public void initCols() {
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
        Callback<TableColumn<Service,String>, TableCell<Service,String>> cellFactory =
                new Callback<TableColumn<Service, String>, TableCell<Service, String>>() {
                    @Override
                    public TableCell<Service, String> call(final TableColumn<Service, String> param) {
                        final TableCell<Service,String> cell = new TableCell<Service,String>(){
                            final Button b=new Button("Delete");

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty){
                                    setGraphic(null);
                                    setText(null);
                                }
                                else{
                                    b.setOnAction(event -> {
                                        //delete service
                                        Service serv=getTableView().getItems().get(getIndex());
                                        CustomerServicesListController s = new CustomerServicesListController();
                                        ObservableList<Service> services = FXCollections.observableArrayList();
                                        services = s.getSelected();
                                        services.removeIf(a->a.equals(serv));
                                    });
                                    setGraphic(b);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        deleteColumn.setCellFactory(cellFactory);
    }

    public void setSelectedservices(ObservableList<Service> s) {
        selectedservices = s;
    }

    public void handleBackButton() {
        //System.out.println(selectedservices);
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerPage.fxml"));
            Scene customerScene = new Scene(viewCustomerPageRoot, 600, 380);
            stage.setScene(customerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  handleFinishButton() throws IOException, EqualHour {
        CustomerServicesListController sc = new CustomerServicesListController();
        ObservableList<Service> selectedservice = FXCollections.observableArrayList();
        selectedservice = sc.getSelected();
        boolean check=false;
        String choiceBoxHour = (String) hour.getValue();
        //System.out.print(" "+choiceBoxHour);
        ArrayList<Appointment> appointms =new ArrayList<>();
        //ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        appointments = AppointmentService.returnAppointments();
        Appointment ap1;
        ArrayList<Service> s1 = new ArrayList<>();
        ArrayList<ArrayList<Service>> s2 = new ArrayList<>();
        ObservableList<String> all_employees=FXCollections.observableArrayList();
        all_employees= EmployeeService.returnEmpUser();
        for(String username:all_employees){
            s1=new ArrayList<>();
            for (Service s: selectedservice){
                if(s.getEmpl().equals(username)){
                    s1.add(s);
                }
            }
            if(!s1.isEmpty())
                s2.add(s1);
        }
        //System.out.println(s2);
        AppointmentService as=new AppointmentService();
        String client_username=as.getClientusr();
        try {
            if(!datePicker.getValue().equals(null)) {
                for(ArrayList<Service> aux:s2) {
                    ap1 = new Appointment(true, datePicker.getValue() + " " + choiceBoxHour, aux.get(0).getEmpl(), client_username, aux);
                    for (Appointment ap : appointments)
                        if ((ap1.getEmpl().equals(ap.getEmpl()) && ap1.getDate().equals(ap.getDate())))
                            check = true;

                    if (check==false)
                    { appointms.add(ap1);
                        //AppointmentService.addAppointment(appointms);

                        Stage box = new Stage();
                        box.initModality(Modality.APPLICATION_MODAL);
                        VBox alertscene = new VBox(30);
                        alertscene.setMinSize(200,100);
                        Label aLabel=new Label();
                        aLabel.setText("Choose one:");
                        HBox hb=new HBox();
                        hb.setSpacing(10);
                        hb.setAlignment(Pos.CENTER);
                        Button closeB=new Button("View my appointments");
                        closeB.setOnAction(e->{
                            box.close();
                            try{
                                Stage stage = (Stage) finishButton.getScene().getWindow();
                                Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerViewAppointmentsPage.fxml"));
                                Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
                                stage.setScene(customerScene);
                            } catch (IOException ex){
                                ex.printStackTrace();
                            }
                        });
                        Button finishTheAppointments=new Button("Finish the appointments");
                        finishTheAppointments.setOnAction(e->{
                            box.close();
                            try {
                                AppointmentService.addAppointment(appointms);
                                CustomerCartController.selectedservices=null;//setam pe null ca nu mai avem servicii selectate
                                initialize();
                            } catch (EqualHour | IOException equalHour) {
                                equalHour.printStackTrace();
                            }
                            try{
                                Stage stage = (Stage) finishButton.getScene().getWindow();
                                Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/Customer_Cart.fxml"));
                                Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
                                stage.setScene(customerScene);
                            } catch (IOException ex){
                                ex.printStackTrace();
                            }
                        });
                        hb.getChildren().addAll(finishTheAppointments,closeB);
                        alertscene.getChildren().addAll(aLabel,hb);
                        alertscene.setAlignment(Pos.CENTER);
                        Scene scene = new Scene(alertscene);
                        box.setScene(scene);
                        box.show();
                    }
                        else
                        throw new EqualHour();
                }
            }
        }catch (EqualHour ee){
            Stage alert = new Stage();
            alert.initModality(Modality.APPLICATION_MODAL);
            VBox alertscene = new VBox(20);
            alertscene.setMinSize(200, 100);
            Label aLabel = new Label();
            aLabel.setText("The chosen date is unavailable, please choose another one!");
            Button closeB = new Button("Close");
            closeB.setOnAction(e -> alert.close());
            alertscene.getChildren().addAll(aLabel, closeB);
            alertscene.setAlignment(Pos.CENTER);
            Scene scene = new Scene(alertscene);
            alert.setScene(scene);
            alert.show();

        } catch (NullPointerException e1) {
            Stage alert = new Stage();
            alert.initModality(Modality.APPLICATION_MODAL);
            VBox alertscene = new VBox(20);
            alertscene.setMinSize(200, 100);
            Label aLabel = new Label();
            aLabel.setText("Please pick a date!");
            Button closeB = new Button("Close");
            closeB.setOnAction(e -> alert.close());
            alertscene.getChildren().addAll(aLabel, closeB);
            alertscene.setAlignment(Pos.CENTER);
            Scene scene = new Scene(alertscene);
            alert.setScene(scene);
            alert.show();
        }
    }

    public void handleGoToMyApps(ActionEvent actionEvent) {
        try{
            Stage stage = (Stage) goToMyApps.getScene().getWindow();
            Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerViewAppointmentsPage.fxml"));
            Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
            stage.setScene(customerScene);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}