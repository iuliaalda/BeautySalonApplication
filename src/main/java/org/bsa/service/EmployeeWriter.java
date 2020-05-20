package org.bsa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class EmployeeWriter {
    /*public static void main(String argv[]){
        //adds employees with respective services to json file
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Service> s1=new ArrayList<>();
        ArrayList<Service> s2=new ArrayList<>();
        s1.add(new Service("Simple Makeup",50));
        s1.add(new Service("Bridal Makeup",60));
        s2.add(new Service("Short Hairstyle",40));
        s2.add(new Service("Long Hairstyle",55));
        Employee e1 = new Employee( "Iulia","abcde","Iulia", "Alda", 20, "makeup", 2,s1);
        Employee e2 = new Employee( "Bia","xyz","Bianka", "Beuka", 21, "hair stylist", 3,s2);
        employees.add(e1);
        employees.add(e2);
        Path EMP_PATH = FileSystemService.getPathToFile("config", "\\employees.json");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src/main/resources/employees.json"),employees);
        }catch (IOException e){e.printStackTrace();}
    }*/
}
