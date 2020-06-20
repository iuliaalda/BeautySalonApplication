package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.model.User;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EmployeeService {
    static List<Employee> employees;
    public static Path EMP_PATH= FileSystemService.getPathToFile("config", "employees.json");
    public static void writeEmployees(){
        //adds employees with respective services to json file
        ArrayList<Employee> employee = new ArrayList<>();
        ArrayList<Service> s1=new ArrayList<>();
        ArrayList<Service> s2=new ArrayList<>();
        s1.add(new Service("Simple Makeup",50,"Iulia"));
        s1.add(new Service("Bridal Makeup",60,"Iulia"));
        s2.add(new Service("Short Hairstyle",40,"Bia"));
        s2.add(new Service("Long Hairstyle",55,"Bia"));
        Employee e1 = new Employee( "Iulia","abcde","Iulia", "Alda", 20, "makeup", 2,s1);
        Employee e2 = new Employee( "Bia","xyz","Bianka", "Beuka", 21, "hair stylist", 3,s2);
        employee.add(e1);
        employee.add(e2);
        Path EMP_PATH = FileSystemService.getPathToFile("config", "employees.json");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src\\main\\resources\\employees.json"),employee);
        }catch (IOException e){e.printStackTrace();}
    }
    public static void writeServicetoEmployee(String username,Service s,File writeFile,File sfile){
        //adding service s to employee emp in json file

        ObservableList<Employee> e;
        e=returnEmp();
        for(Employee employee:e){
            if(employee.getUsername().equals(username))
            {
                employee.addtoServiceList(s);
                ServicesService.addService(s,sfile);
            }
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writeFile,e);
        }catch (IOException exception){exception.printStackTrace();}

    }
    public static void updateServiceinEmployee(String username,Service s,String newname,float newprice,File writeFile,File sfile){
        ObservableList<Employee> e;
        e=returnEmp();
        for(Employee employee:e){
            if(employee.getUsername().equals(username))
            {
                employee.updateValue(s,newname,newprice);
                ServicesService.updateServ(s,newname,newprice,sfile);
            }
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writeFile,e);
        }catch (IOException exception){exception.printStackTrace();}
    }
    public static void removeServicefromEmployee(String username, Service s,File writeFile,File sfile){
        ObservableList<Employee> e;
        e=returnEmp();
        for(Employee employee:e){
            if(employee.getUsername().equals(username))
            {
                employee.removefromServiceList(s);
                ServicesService.removeServ(s,sfile);
            }
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writeFile,e);
        }catch (IOException exception){exception.printStackTrace();}
    }
    public static void loadEmployees(File readFile)throws IOException{
        if(!Files.exists(EMP_PATH)){
            FileUtils.copyURLToFile(User.class.getClassLoader().getResource("employees.json"), EMP_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();

        employees=objectMapper.readValue(readFile, new TypeReference<List<Employee>>() {});
    }
    public static ObservableList<Employee> returnEmp(){
        ObservableList<Employee> aux= FXCollections.observableArrayList();
        for(Employee employee:employees){
            aux.add(employee);
        }
        return aux;
    }
    public static ObservableList<String> returnEmpUser(){
        ObservableList<String> aux= FXCollections.observableArrayList();
        for(Employee employee:employees){
            aux.add(employee.getUsername());
        }
        return aux;
    }
    public static Employee findEmployee(String user){
        for(Employee employee:employees){
            if(employee.getUsername().equals(user))
                return employee;
        }
        return null;
    }

}

