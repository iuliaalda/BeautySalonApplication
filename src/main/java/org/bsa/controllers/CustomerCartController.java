package org.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bsa.model.Service;

import javax.swing.text.TabableView;
import java.io.IOException;


public class CustomerCartController  {
    @FXML
    TableView<Service> tableCart;
    @FXML
    TableColumn serviceColumn;
    @FXML
    TableColumn priceColumn;
    @FXML
    Button backButton;
    static ObservableList<Service> selectedservices= FXCollections.observableArrayList();

    public void initialize(){
        CustomerServicesListController s=new CustomerServicesListController();
        ObservableList<Service> services = FXCollections.observableArrayList();
        services=s.getSelected();
        tableCart.setItems(services);
      // System.out.print(services+"\n ");
        initCols();

    }
    public void initCols(){
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new  PropertyValueFactory<>("price"));

    }
    public void setSelectedservices(ObservableList<Service> s){selectedservices=s;}
    public void handleBackButton(){
        //System.out.println(selectedservices);
        try{
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerPage.fxml"));
            Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
            stage.setScene(customerScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
