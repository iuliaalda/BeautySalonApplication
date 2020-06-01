package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.bsa.model.Appointment;
import org.bsa.model.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private static List<Appointment> appointments;
    static String usr;

    private static final Path A_PATH=FileSystemService.getPathToFile("config","appointments.json");
    public static void writeAppointment() {
        ArrayList<Appointment> appointments=new ArrayList<>();
        ArrayList<Service> s1=new ArrayList<>();
        ArrayList<Service> s2=new ArrayList<>();
        ArrayList<Service> s3=new ArrayList<>();
        s1.add(new Service("Bridal Makeup",75,"Iulia"));
        s1.add(new Service("Simple Makeup",20,"Iulia"));
        s2.add(new Service("Short Hairstyle",40,"Bia"));
        s2.add(new Service("Long Hairstyle",55,"Bia"));
        s3.add(new Service("Long Hairstyle",55,"Bia"));
        appointments.add(new Appointment(true,"2020:06:12 12:00","Iulia",s1));
        appointments.add(new Appointment(true,"2020:06:12 16:00","Bia",s3));
        appointments.add(new Appointment(false,"2020:06:12 8:00","Bia",s2));
        appointments.add(new Appointment(false,"2020:06:12 10:00","Bia",s3));
        Path A_PATH = FileSystemService.getPathToFile("config", "appointments.json");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src/main/resources/appointments.json"),appointments);
        }catch (IOException e){e.printStackTrace();}
    }

    public static void loadAppointments() throws IOException{
        if(!Files.exists(A_PATH)){
            FileUtils.copyURLToFile(AppointmentService.class.getClassLoader().getResource("appointments.json"), new File("src/main/resources/appointments.json"));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        appointments=objectMapper.readValue(new File("src/main/resources/appointments.json"), new TypeReference<List<Appointment>>() {});
    }


    public static ObservableList<Appointment> returnAppointments(){
        ObservableList<Appointment> aux=FXCollections.observableArrayList();
        for(Appointment a:appointments)
            aux.add(a);
        return aux;
    }
    public static ObservableList<Appointment> returnCertainAppointment(){
        ObservableList<Appointment> aux=FXCollections.observableArrayList();
        for(Appointment a:appointments)
        {
            if(a.getEmpl().equals(usr) && a.getStatus().equals(true)) {
                aux.add(a);
            }
        }
        return aux;
    }
    public static ObservableList<Appointment> returnCancelledAppointment(){
        ObservableList<Appointment> aux=FXCollections.observableArrayList();
        for(Appointment a:appointments)
        {
            if(a.getEmpl().equals(usr) && a.getStatus().equals(false)) {
                aux.add(a);
            }
        }
        return aux;
    }
    /*public static ObservableList<Service> returnServicesAppointment(){
        ObservableList<Service> aux=FXCollections.observableArrayList();
        for(Appointment a:appointments) {
            if (a.getEmpl().equals(usr) && a.getStatus().equals(true)) {
                for (Service s : a.getServices())
                    if (s.getType().equals(a.getServices()))
                        aux.add(s);

            }
        }
        return aux;
    }
*/
    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String a) {
    usr = a;
    }







}
