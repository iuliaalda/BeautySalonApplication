package org.bsa.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.service.EmployeeService;
import org.bsa.service.ServicesService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
   static ObservableList<Service> selected=FXCollections.observableArrayList();
    File file = new File("src\\main\\resources\\employees.json");
    File sfile = new File("src\\main\\resources\\services.json");
    public void initialize()throws IOException {
        EmployeeService.loadEmployees(file);
        ServicesService.loadServices(sfile);
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
        Callback<TableColumn<Service,String>, TableCell<Service,String>> cellFactory =
                new Callback<TableColumn<Service, String>, TableCell<Service, String>>() {
                    @Override
                    public TableCell<Service, String> call(final TableColumn<Service, String> param) {
                        final TableCell<Service,String> cell = new TableCell<Service,String>(){
                            final Button b=new Button("Select it");

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty){
                                    setGraphic(null);
                                    setText(null);
                                }
                                else{
                                    b.setOnAction(event -> {
                                        //add/remove to/from list
                                        Service s = getTableView().getItems().get(getIndex());
                                        if(b.getText().equals("Selected")){
                                            //remove
                                            selected.removeIf(serv->serv.equals(s));
                                            b.setText("Select it");
                                        }
                                        else {
                                            selected.add(s);
                                            b.setText("Selected");
                                        }
                                    });
                                    setGraphic(b);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        checkboxColumn.setCellFactory(cellFactory);
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
        //link checkboxes with done button
        //passing selected services to cart
        //go back or to cart
        Stage box = new Stage();
        box.initModality(Modality.APPLICATION_MODAL);
        VBox alertscene = new VBox(20);
        alertscene.setMinSize(200,100);
        Label aLabel=new Label();
        aLabel.setText("Choose one:");
        HBox hb=new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        Button closeB=new Button("Go Back");
        closeB.setOnAction(e->{
            box.close();
            try{
                Stage stage = (Stage) doneButton.getScene().getWindow();
                Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/CustomerPage.fxml"));
                Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
                stage.setScene(customerScene);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        });
        Button cartB=new Button("Go to My Cart");
        cartB.setOnAction(e->{
            box.close();
            //link to my cart page
            CustomerCartController.selectedservices=selected;
            try{
                Stage stage = (Stage) doneButton.getScene().getWindow();
                Parent viewCustomerPageRoot = FXMLLoader.load(getClass().getResource("/Customer_Cart.fxml"));
                Scene customerScene=new Scene(viewCustomerPageRoot,600,380);
                stage.setScene(customerScene);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        });
        hb.getChildren().addAll(cartB,closeB);
        alertscene.getChildren().addAll(aLabel,hb);
        alertscene.setAlignment(Pos.CENTER);
        Scene scene = new Scene(alertscene);
        box.setScene(scene);
        box.show();
    }
    public ObservableList<Service> getSelected(){
        return selected;
    }
}
