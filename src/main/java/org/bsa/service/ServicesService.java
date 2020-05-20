package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.bsa.controllers.LoginController;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ServicesService {
    private static List<Service> services;
    static String usr;
    private static final Path S_PATH=FileSystemService.getPathToFile("config","\\services.json");

    public static void addServices() throws IOException{
        ObservableList<Employee> employeeList=FXCollections.observableArrayList();
        ArrayList<Service> s=new ArrayList<>();
        EmployeeService.loadEmployees();
        employeeList=EmployeeService.returnEmp();
        for(Employee aux1:employeeList){
            for(Service aux2:aux1.getListServices())
                s.add(aux2);
        }
        services=s;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/services.json");
            objectMapper.writeValue(file,services);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void loadServices() throws IOException{
        if(!Files.exists(S_PATH)){
            FileUtils.copyURLToFile(Service.class.getClassLoader().getResource("\\services.json"), new File("src/main/resources/services.json"));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/services.json");
        services=objectMapper.readValue(file, new TypeReference<List<Service>>() {});
    }
    public static ObservableList<Service> returnServ(){
        ObservableList<Service> aux=FXCollections.observableArrayList();
        for(Service s:services)
            aux.add(s);
        return aux;
    }
    public static void addService(Service s){
        ObservableList<Service> service;
        service=returnServ();
        service.add(s);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/services.json");
            objectMapper.writeValue(file,service);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void updateServ(Service s,String name,float price){
        ObservableList<Service> service;
        service=returnServ();
        for(Service aux:service){
            if(aux.equals(s)){
                aux.setType(name);
                aux.setPrice(price);
            }
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/services.json");
            objectMapper.writeValue(file,service);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void removeServ(Service s){
        ObservableList<Service> service;
        service=returnServ();
        service.removeIf(serv->serv.equals(s));
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/services.json");
            objectMapper.writeValue(file,service);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setUsr(String s){usr=s;}
    public String getUsr(){return usr;}
    public static ObservableList<Service> returnCertainServ(){
        ObservableList<Service> aux=FXCollections.observableArrayList();
        for(Service s:services)
        {
            if(s.getEmpl().equals(usr)) {
                aux.add(s);
            }
        }
        return aux;
    }
}
