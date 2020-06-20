package org.bsa.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.apache.commons.io.FileUtils;
import org.bsa.model.Employee;
import org.bsa.service.EmployeeService;
import org.bsa.service.FileSystemService;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.text.TabExpander;
import javax.swing.text.html.ListView;
import java.nio.file.FileSystem;
import java.util.List;


public class CustomerStaffListController {
    @FXML
    TableView<Employee> tableStaff;
    @FXML
    TableColumn<Employee,String> fName;
    @FXML
    TableColumn<Employee,String> lName;
    @FXML
    TableColumn<Employee,String> age;
    @FXML
    TableColumn<Employee,String> service;
    @FXML
    TableColumn<Employee,String> yrsExp;
    @FXML
    Button backButton;
    File file = new File("src\\main\\resources\\employees.json");
    public void initialize() throws IOException{
        EmployeeService.loadEmployees(file);
        fName.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
        lName.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
        age.setCellValueFactory(new PropertyValueFactory<Employee,String>("age"));
        service.setCellValueFactory(new PropertyValueFactory<Employee,String>("serviceType"));
        yrsExp.setCellValueFactory(new PropertyValueFactory<Employee,String>("yearsExperience"));
        //tableStaff.setItems(getEmployees());
        tableStaff.setItems(EmployeeService.returnEmp());
    }

    @FXML
    void handleBackButton(){
        try{
            Stage stage = (Stage) tableStaff.getScene().getWindow();
            Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerPage.fxml"));
            Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
            stage.setScene(customerScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
