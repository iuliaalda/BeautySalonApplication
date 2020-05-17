package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.bsa.model.Employee;
import org.bsa.model.User;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class EmployeeService {
    private static List<Employee> employees;
    private static final Path EMP_PATH= FileSystemService.getPathToFile("config", "\\employees.json");


    public static void loadEmployees()throws IOException{
        if(!Files.exists(EMP_PATH)){
            FileUtils.copyURLToFile(User.class.getClassLoader().getResource("\\employees.json"), new File("src/main/resources/employees.json"));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/employees.json");
        //employees= Arrays.asList(objectMapper.readValue(Paths.get("\\employees.json").toFile(), Employee[].class));
        employees=objectMapper.readValue(file, new TypeReference<List<Employee>>() {});
        //System.out.println("Read employees"+employees);
    }
    public static ObservableList<Employee> returnEmp() throws IOException{
        ObservableList<Employee> aux= FXCollections.observableArrayList();
        for(Employee employee:employees){
            aux.add(employee);
        }
        return aux;
    }
}
