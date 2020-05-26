package org.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.bsa.model.Service;

import java.io.IOException;


public class CustomerCartController {
    @FXML
    Button backButton;
    static ObservableList<Service> selectedservices= FXCollections.observableArrayList();
    public void initialize(){
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
