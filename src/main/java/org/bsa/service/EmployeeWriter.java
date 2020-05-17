package org.bsa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bsa.model.Employee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class EmployeeWriter {
    public static void main(String argv[]){
        ArrayList<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee( "Iulia", "Alda", 20, "makeup", 2);
        Employee e2 = new Employee( "Bianka", "Beuka", 21, "hair stylist", 3);
        employees.add(e1);
        employees.add(e2);

        Path EMP_PATH = FileSystemService.getPathToFile("config", "\\employees.json");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src/main/resources/employees.json"),employees);
        }catch (IOException e){e.printStackTrace();}
    }
}
