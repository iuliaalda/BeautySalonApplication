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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    Button backButton;
    @FXML
    ChoiceBox hour = new ChoiceBox();
    @FXML
    Label display;
    @FXML
    DatePicker datePicker = new DatePicker();
    @FXML
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
    // Date date = c.getTime();

    /*  @FXML
             SimpleDateFormat format = new SimpleDateFormat(":mm:ss");
             dateField.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00:00")));*/
    @FXML
    public void initializeChoiceBox() {

        /*(year.getItems().addAll("2020","2021");

        day.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,26,27,28,29,30,31);
        day.setValue("1");
        year.setValue("2020");
        month.getItems().addAll("January","February","March","April","June","July","August","September","October","November","December");
        month.setValue("January");*/
        hour.getItems().addAll("8:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00");
        hour.setValue("8:00");
    }


    /*  @FXML
      SimpleDateFormat format = new SimpleDateFormat(":mm:ss");
      dateField.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00:00")));*/
    static ObservableList<Service> selectedservices = FXCollections.observableArrayList();

    /*public  void handleDate{
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy:MM:dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

    }*/
    public void initialize() {
       /* format = new SimpleDateFormat("yyyy:MM:dd HH:mm");
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate date=datePicker.getValue();
                System.out.print(date);
                display.setText(date.toString());
            }
        });*/
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

    public String handleDate() {
        // String dateString;
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy:MM:dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePicker.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        return datePicker.toString();
    }
    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    public ObservableList<Appointment>  handleFinishButton() throws IOException {
        CustomerServicesListController sc = new CustomerServicesListController();
        ObservableList<Service> selectedservice = FXCollections.observableArrayList();
        selectedservice = sc.getSelected();

        String choiceBoxHour = (String) hour.getValue();
        //System.out.print(" "+choiceBoxHour);
       ArrayList<Appointment> appointms =new ArrayList<>();
        //ObservableList<Appointment> appointments=FXCollections.observableArrayList();
        AppointmentService.loadAppointments();
        appointments = AppointmentService.returnCertainAppointment();
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
        System.out.println(s2);
        try {
        if(!datePicker.getValue().equals(null)) {
            for(ArrayList<Service> aux:s2){
                ap1 = new Appointment(true, datePicker.getValue() + " " + choiceBoxHour, aux.get(0).getEmpl(), aux);
                appointms.add(ap1);
            }

        }

     }catch (NullPointerException ee){
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
        try {
            for (Appointment a : appointms)
                for (Appointment ap : appointments)
                    if (!a.getEmpl().equals(ap.getEmpl()) && a.getDate().equals(ap.getEmpl()))
                        appointments.add(a);
                    else
                        throw new EqualHour();
        } catch (EqualHour e1) {
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
        }
       // appointments.addAll(appointms);
        System.out.println(appointms);
        return  appointments;
    }
}