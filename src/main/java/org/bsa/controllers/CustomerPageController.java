package org.bsa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bsa.service.EmployeeService;

import java.io.IOException;

public class CustomerPageController {

    @FXML
    Label welcomeCustomerLabel;
    public void handleViewStaffButton() throws IOException{
        try{
            Scene listScene;
            Stage stage = (Stage) welcomeCustomerLabel.getScene().getWindow();
            Parent viewStaffListPageRoot = FXMLLoader.load(getClass().getResource("/customerStaffList.fxml"));
            listScene= new Scene(viewStaffListPageRoot,600,380);
            stage.setScene(listScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleViewServices(){
        try{
            Scene listScene;
            Stage stage = (Stage) welcomeCustomerLabel.getScene().getWindow();
            Parent viewStaffListPageRoot = FXMLLoader.load(getClass().getResource("/CustomerServicesList.fxml"));
            listScene= new Scene(viewStaffListPageRoot,600,380);
            stage.setScene(listScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleCartAction(){
        try{
            Stage stage = (Stage) welcomeCustomerLabel.getScene().getWindow();
            Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/Customer_Cart.fxml"));
            Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
            stage.setScene(customerScene);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void handleAppointmentAction(ActionEvent actionEvent) {
        try{
            Stage stage = (Stage) welcomeCustomerLabel.getScene().getWindow();
            Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerViewAppointmentsPage.fxml"));
            Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
            stage.setScene(customerScene);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
