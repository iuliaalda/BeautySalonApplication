package org.bsa.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import org.bsa.model.Service;

public class EmployeeServicesListController {

    @FXML
    private TableView<Service> employeeServices;
    @FXML
    private TableColumn<Service, Button> edit;
    @FXML
    private TableColumn<Service, String> service;
    @FXML
    private TableColumn<Service, String> price;
    @FXML
    private TableColumn<Service, Button> delete;

    public void initialize(){

    }
    private void initTable(){

    }
    private void initCol(){

    }
    private void editCol(){

    }
}
