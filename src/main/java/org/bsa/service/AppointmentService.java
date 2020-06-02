package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
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
        appointments.add(new Appointment(false,"2020:06:12 14:00","Iulia",s1));
        appointments.add(new Appointment(false,"2020:06:12 16:00","Bia",s3));
        appointments.add(new Appointment(true,"2020:06:12 15:00","Bia",s3));
        appointments.add(new Appointment(false,"2020:06:12 8:00","Bia",s2));
        appointments.add(new Appointment(false,"2020:06:12 10:00","Bia",s3));
        appointments.add(new Appointment(true,"2020:06:12 15:00","Iulia",s1));
        appointments.add(new Appointment(false,"2020:06:12 17:00","Iulia",s1));

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

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String a) {
    usr = a;
    }
    public static void removeCancelled(Appointment a){
        ObservableList<Appointment> appointments;
        appointments=returnCancelledAppointment();
        appointments.removeIf(app->app.equals(a));
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/appointments.json");
            objectMapper.writeValue(file,appointments);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void removeAppsfromCancelled( Appointment a) //String usr,
    {
        ObservableList<Appointment> apps=FXCollections.observableArrayList();
        //apps=returnCancelledAppointment();
       // System.out.println("cancelled"+apps.toString());
        ObservableList<Appointment> toRemove=FXCollections.observableArrayList();
            for (Appointment appointment : apps) {
                if (appointment.equals(a) && a.getEmpl().equals(usr) && appointment.getDate().equals(a.getDate()))// && a.getEmpl().equals(usr)  appointment.getDate().equals(a.getDate())
                {
                  toRemove.add(a);
                   // System.out.println("\ncancelled"+a.toString());
                    //AppointmentService.removeCancelled(a);
                }

            }
            apps.remove(toRemove);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/appointments.json");
            objectMapper.writeValue(file,apps);
            objectMapper.writeValue(file, AppointmentService.returnCertainAppointment());
        }catch (IOException exception){exception.printStackTrace();}
    }



}








