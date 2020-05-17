package org.bsa.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import org.bsa.model.Service;
import org.bsa.service.ServicesService;

import java.awt.*;
import java.io.IOException;

public class EmployeeServicesListController {

    @FXML
    private TableView<Service> employeeServices;
    @FXML
    private TableColumn edit;
    @FXML
    private TableColumn service;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn delete;

    public void initialize() throws IOException {
        ServicesService.loadServices();
        initTable();
    }
    private void initTable(){
        initCol();
        loadServices();
    }
    private void initCol(){
        service.setCellValueFactory(new PropertyValueFactory<>("type"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        Callback<TableColumn<Service,String>, TableCell<Service,String>> cellFactory =
                new Callback<TableColumn<Service, String>, TableCell<Service, String>>() {
                    @Override
                    public TableCell<Service, String> call(final TableColumn<Service, String> param) {
                        final TableCell<Service,String> cell = new TableCell<Service,String>(){
                            final Button b=new Button("Edit");

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty){
                                    setGraphic(null);
                                    setText(null);
                                }
                                else{
                                    b.setOnAction(event -> {
                                        Service service = getTableView().getItems().get(getIndex());
                                        System.out.println(service.getType()+" "+service.getPrice());
                                    });
                                    setGraphic(b);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        edit.setCellFactory(cellFactory);
        //editCol();
    }
    /*private void editCol(){
        service.setCellFactory(TextFieldTableCell.forTableColumn());
        service.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue());
        });
        price.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        price.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
        });

        employeeServices.setEditable(true);
    }*/

    private void loadServices(){
        //from json file, maybe call method from services service class
        ObservableList<Service> services= FXCollections.observableArrayList();
        services.add(new Service("Makeup",50));
        services.add(new Service("Hairstyle",40));
        employeeServices.setItems(services);
    }
}
