package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.bsa.Main;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceTest {
    File empfile = new File("src\\test\\resources\\employees.json");
    File sfile = new File("src\\test\\resources\\services.json");
    @BeforeClass
    public static void beforeClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("employees.json"),new File("src/test/resources/employees.json"));
        FileUtils.copyURLToFile(Employee.class.getClassLoader().getResource("services.json"),new File("src/test/resources/services.json"));
    }

    @Test
    public void writeEmployees() {

    }

    @Test
    public void writeServicetoEmployee() throws Exception{
        EmployeeService.loadEmployees(empfile);
        ServicesService.loadServices(sfile);
        String employee_user_test="Bia";
        Service service_test = new Service("Medium Hairstyle",50.0f,employee_user_test);
        EmployeeService.writeServicetoEmployee(employee_user_test,service_test,empfile,sfile);
        ObservableList<Employee> emp=EmployeeService.returnEmp();
        assertNotNull(emp);
        Employee employee_test=EmployeeService.findEmployee(employee_user_test);
        assertEquals(2,employee_test.getListServices().size());
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("employees.json"), empfile);
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("services.json"), sfile);
    }

    @Test
    public void updateServiceinEmployee() throws Exception{
        EmployeeService.loadEmployees(empfile);
        ServicesService.loadServices(sfile);
        String employee_user_test="Iulia";
        String name="testName";
        Service saux=new Service();
        Service stest =new Service("Bridal Makeup",60.0f,"Iulia");
        EmployeeService.updateServiceinEmployee("Iulia",stest,name,stest.getPrice(),empfile,sfile);
        ObservableList<Service> s=ServicesService.returnServ();
        for(Service aux:s){
            if(aux.getPrice()==stest.getPrice())
                saux=aux;
        }
        assertEquals(saux.getType(),name);
        //FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("employees.json"), empfile);
        //FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("services.json"), sfile);
    }

    @Test
    public void removeServicefromEmployee() throws Exception{
        EmployeeService.loadEmployees(empfile);
        ServicesService.loadServices(sfile);
        String employee_user_test="Iulia";
        Employee E=new Employee();
        Service stest =new Service("Bridal Makeup",60.0f,"Iulia");
        ObservableList<Service> s=ServicesService.returnServ();
        EmployeeService.removeServicefromEmployee("Iulia",stest,empfile,sfile);
        ObservableList<Employee> empls=EmployeeService.returnEmp();
        for(Employee aux:empls)
            if(aux.getUsername().equals(employee_user_test))
                E=aux;
        assertEquals(1,E.getListServices().size());
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("employees.json"), empfile);
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("services.json"), sfile);
    }

    @Test
    public void loadEmployees() throws Exception{
        EmployeeService.loadEmployees(empfile);
        assertNotNull(EmployeeService.employees);
        assertEquals(2,EmployeeService.employees.size());
    }

    @Test
    public void returnEmp() throws Exception{
        EmployeeService.loadEmployees(empfile);
        assertNotNull(EmployeeService.employees);
        ObservableList<Employee> empls= FXCollections.observableArrayList();
        empls=EmployeeService.returnEmp();
        assertNotNull(empls);
        assertEquals(2,empls.size());
    }

    @Test
    public void returnEmpUser() throws Exception{
        EmployeeService.loadEmployees(empfile);
        assertNotNull(EmployeeService.employees);
        ObservableList<String> empls= FXCollections.observableArrayList();
        empls=EmployeeService.returnEmpUser();
        assertNotNull(empls);
        assertEquals(2,empls.size());
    }

    @Test
    public void findEmployeeOne() throws Exception{
        EmployeeService.loadEmployees(empfile);
        assertNotNull(EmployeeService.employees);
        String test_user="Iulia";
        Employee test_employee=EmployeeService.findEmployee(test_user);
        assertNotNull(test_employee);
    }
    @Test
    public void findEmployeeTwo() throws Exception{
        EmployeeService.loadEmployees(empfile);
        assertNotNull(EmployeeService.employees);
        String test_user="test";
        Employee test_employee=EmployeeService.findEmployee(test_user);
        assertNull(test_employee);
    }
}