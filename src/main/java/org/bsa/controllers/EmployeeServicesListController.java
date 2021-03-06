package org.bsa.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.service.EmployeeService;
import org.bsa.service.ServicesService;


import java.io.File;
import java.io.IOException;

public class EmployeeServicesListController {

    @FXML
     TableView<Service> employeeServices;
    @FXML
     TableColumn edit;
    @FXML
     TableColumn service;
    @FXML
     TableColumn price;
    @FXML
     TableColumn delete;
    @FXML
     Button addBttn;
    @FXML
    TextField serviceAddText;
    @FXML
    TextField priceAddText;
    public File efile=new File("src\\main\\resources\\employees.json");
    public File sfile = new File("src\\main\\resources\\services.json");
    public void handleAddAction(){
        Service service=new Service();
        ServicesService ss=new ServicesService();
        if((!serviceAddText.getText().isEmpty()) && (!priceAddText.getText().isEmpty())){
            service.setType(serviceAddText.getText());
            try{
                Float.parseFloat(priceAddText.getText());
                service.setPrice(Float.parseFloat(priceAddText.getText()));
                service.setEmpl(ss.getUsr());
                //add to database
                EmployeeService.writeServicetoEmployee(ss.getUsr(),service,efile,sfile);
            }catch (NumberFormatException exception) {
                Stage alert = new Stage();
                alert.initModality(Modality.APPLICATION_MODAL);
                VBox alertscene = new VBox(20);
                alertscene.setMinSize(200,100);
                Label aLabel = new Label();
                aLabel.setText("The price field is not a number!");
                Button closeB = new Button("Close");
                closeB.setOnAction(e -> alert.close());
                alertscene.getChildren().addAll(aLabel, closeB);
                alertscene.setAlignment(Pos.CENTER);
                Scene scene = new Scene(alertscene);
                alert.setScene(scene);
                alert.show();
            }
        }
        else
        {
            Stage alert = new Stage();
            alert.initModality(Modality.APPLICATION_MODAL);
            VBox alertscene = new VBox(20);
            alertscene.setMinSize(200,100);
            Label aLabel=new Label();
            aLabel.setText("Please fill all the fields!");
            Button closeB=new Button("Close");
            closeB.setOnAction(e->alert.close());
            alertscene.getChildren().addAll(aLabel,closeB);
            alertscene.setAlignment(Pos.CENTER);
            Scene scene = new Scene(alertscene);
            alert.setScene(scene);
            alert.show();
        }

        //clear inputs
        serviceAddText.clear();
        priceAddText.clear();
    }

    public void initialize() throws IOException {
        EmployeeService.loadEmployees(efile);
        ServicesService.loadServices(sfile);
        initTable();
    }
    public void initTable(){
        initCol();
        ObservableList<Service> aux=FXCollections.observableArrayList();
        aux=ServicesService.returnCertainServ();
        employeeServices.setItems(aux);
    }
    public void initCol(){
        ServicesService ss=new ServicesService();
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
                                        Service s=getTableView().getItems().get(getIndex());
                                        String ename=new String(); float eprice=0;
                                        try{
                                            Stage stage = new Stage();
                                            FXMLLoader loader=new FXMLLoader(getClass().getResource("/EmployeeEditingPage.fxml"));
                                            Parent vieweditingPageRoot = loader.load();
                                            Scene editingScene=new Scene(vieweditingPageRoot,300,300);
                                            stage.setScene(editingScene);
                                            stage.showAndWait();
                                            ename=loader.<EmployeeEditingController>getController().getNewName();
                                            eprice=loader.<EmployeeEditingController>getController().getNewPrice();
                                        } catch (IOException e){
                                            e.printStackTrace();
                                        }
                                        //Service editedService=new Service(ename,eprice,ss.getUsr());

                                        if(ename==null)
                                            ename=s.getType();
                                        if(eprice==0.0)
                                            eprice=s.getPrice();
                                        EmployeeService.updateServiceinEmployee(ss.getUsr(),s,ename,eprice,efile,sfile);
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
                                        Service s=getTableView().getItems().get(getIndex());
                                        EmployeeService.removeServicefromEmployee(ss.getUsr(),s,efile,sfile);
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

    @FXML
    void handleBackButton(){
        try{
            Stage stage = (Stage) addBttn.getScene().getWindow();
            Parent viewEmployeePageRoot = FXMLLoader.load(getClass().getResource("/EmployeePage.fxml"));
            Scene employeeScene=new Scene(viewEmployeePageRoot,600,380);
            stage.setScene(employeeScene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
