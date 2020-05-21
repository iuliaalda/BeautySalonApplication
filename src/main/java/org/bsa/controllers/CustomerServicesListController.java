package org.bsa.controllers;

import com.sun.javaws.IconUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.service.EmployeeService;
import org.bsa.service.ServicesService;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class CustomerServicesListController {
    @FXML
    private TableView<Service> custServTable;

    @FXML
    private TableColumn nameColumn;

    @FXML
    private TableColumn checkboxColumn;

    @FXML
    private TableColumn priceColumn;

    @FXML
    private Button doneButton;
    public void initialize()throws IOException {
        EmployeeService.loadEmployees();
        ServicesService.loadServices();
        initTable();
    }
    public void initTable(){
        initCols();
        ObservableList<Service> service= FXCollections.observableArrayList();
        service=ServicesService.returnServ();
        custServTable.setItems(service);
        custServTable.setRowFactory(tv->{
            TableRow<Service> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Service clickedRow = row.getItem();
                    //System.out.println(clickedRow.toString());
                    //opening stage with information about the employee
                    Stage info = new Stage();
                    info.initModality(Modality.APPLICATION_MODAL);
                    VBox infoscene = new VBox(10);
                    infoscene.setMinSize(250,200);
                    Label typeLabel=new Label();
                    Label nLabel=new Label();
                    Label prLabel=new Label();
                    Label yrLabel=new Label();
                    String user=clickedRow.getEmpl();
                    Employee emp=EmployeeService.findEmployee(user);
                    typeLabel.setText("Selected service: "+clickedRow.getType());
                    nLabel.setText("Name: "+emp.getFirstName()+" "+emp.getLastName());
                    prLabel.setText("Profession: "+emp.getServiceType());
                    yrLabel.setText("Years of Experience: "+Integer.toString(emp.getYearsExperience()));
                    Button closeB=new Button("Close");
                    closeB.setOnAction(e->info.close());
                    infoscene.getChildren().addAll(typeLabel,nLabel,prLabel,yrLabel,closeB);
                    infoscene.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(infoscene);
                    info.setScene(scene);
                    info.show();
                }
            });
            return row ;
        });
    }
    public void initCols(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        checkboxColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures param) {
                CheckBox check = new CheckBox();
                return new SimpleObjectProperty<CheckBox>(check);
            }
        });

        //checkboxColumn.setCellFactory(cellFactory);
        checkboxColumn.setEditable(true);
    }

    @FXML
    void handleBackButton(){
        try{
            Stage stage = (Stage) doneButton.getScene().getWindow();
            Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerPage.fxml"));
            Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
            stage.setScene(customerScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void handleDoneButton(){
        System.out.println("Pressed");
    }

}
