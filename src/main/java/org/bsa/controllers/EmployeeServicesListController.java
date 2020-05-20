package org.bsa.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.service.EmployeeService;
import org.bsa.service.ServicesService;


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
    @FXML
    private Button addBttn;
    @FXML
    private TextField serviceAddText;
    @FXML
    private TextField priceAddText;

    public void handleAddAction(){
        Service service=new Service();
        ServicesService ss=new ServicesService();
        service.setType(serviceAddText.getText());
        service.setPrice(Float.parseFloat(priceAddText.getText()));
        service.setEmpl(ss.getUsr());
        //add to database
        Employee e;
        //e=EmployeeService.findEmployee(ss.getUsr());
        /*e.addtoServiceList(service);
        System.out.println(e.toString());*/
        EmployeeService.writeServicetoEmployee(ss.getUsr(),service);

        //clear inputs
        serviceAddText.clear();
        priceAddText.clear();
    }

    public void initialize() throws IOException {
        EmployeeService.loadEmployees();
        ServicesService.loadServices();
        initTable();
    }
    private void initTable(){
        initCol();
        ObservableList<Service> aux=FXCollections.observableArrayList();
        aux=ServicesService.returnCertainServ();
        employeeServices.setItems(aux);
    }
    private void initCol(){
        service.setCellValueFactory(new PropertyValueFactory<>("type"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        //handling edit button action
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
                                        //editing the row

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
        //the delete button now
        Callback<TableColumn<Service,String>, TableCell<Service,String>> cellFactoryDelete =
                new Callback<TableColumn<Service, String>, TableCell<Service, String>>() {
                    @Override
                    public TableCell<Service, String> call(final TableColumn<Service, String> param) {
                        final TableCell<Service,String> cell = new TableCell<Service,String>(){
                            final Button b2=new Button("Delete");

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty){
                                    setGraphic(null);
                                    setText(null);
                                }
                                else{
                                    b2.setOnAction(event -> {
                                        //deleting the row
                                        System.out.println("Deleted the item");
                                    });
                                    setGraphic(b2);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        delete.setCellFactory(cellFactoryDelete);
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

}
