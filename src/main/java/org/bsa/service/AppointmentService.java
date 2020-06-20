package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.bsa.controllers.CustomerCartController;
import org.bsa.exceptions.EqualHour;
import org.bsa.model.Appointment;
import org.bsa.model.Employee;
import org.bsa.model.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentService {
    private static List<Appointment> appointments;
    static String usr;
    static String clientusr;
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
        appointments.add(new Appointment(true,"2020-06-19 12:00","Iulia","A",s1));
        appointments.add(new Appointment(false,"2020-06-21 14:00","Iulia","A",s1));
        appointments.add(new Appointment(false,"2020-06-12 16:00","Bia","B",s3));
        appointments.add(new Appointment(true,"2020-06-25 15:00","Bia","B",s3));
        appointments.add(new Appointment(false,"2020-06-12 8:00","Bia","A",s2));
        appointments.add(new Appointment(false,"2020-06-12 10:00","Bia","A",s3));
        appointments.add(new Appointment(true,"2020-06-12 15:00","Iulia","B",s1));
        appointments.add(new Appointment(false,"2020-06-12 17:00","Iulia","B",s1));

        Path A_PATH = FileSystemService.getPathToFile("config", "appointments.json");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src\\main\\resources\\appointments.json"),appointments);
        }catch (IOException e){e.printStackTrace();}
    }

    public static void loadAppointments(File afile) throws IOException{
        if(!Files.exists(A_PATH)){
            FileUtils.copyURLToFile(AppointmentService.class.getClassLoader().getResource("appointments.json"), A_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        appointments=objectMapper.readValue(afile, new TypeReference<List<Appointment>>() {});
    }


    public static ObservableList<Appointment> returnAppointments(){
        ObservableList<Appointment> aux=FXCollections.observableArrayList();
        for(Appointment a:appointments)
            aux.add(a);
        return aux;
    }

    public static ObservableList<Appointment> returnTodayApps(){
        ObservableList<Appointment> aux=FXCollections.observableArrayList();

        for(Appointment a:appointments)
        {
            if(a.getEmpl().equals(usr) && a.getStatus().equals(true)) {
                String[] app_date = a.getDate().split("-| ");
                Date d = new Date();
                String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
                int d1,d2;
                String[] date=modifiedDate.split("-");
                d1=Integer.parseInt(app_date[2]);
                d2=Integer.parseInt(date[2]);
                if(app_date[1].equals(date[1])&&(d1-d2==0) ) {
                aux.add(a);
                }
            }
        }
        return aux;
    }
    public static ObservableList<Appointment> returnNextCancelledApps(){
        ObservableList<Appointment> aux=FXCollections.observableArrayList();

        for(Appointment a:appointments)
        {
            if(a.getEmpl().equals(usr) && a.getStatus().equals(false)) {
                String[] app_date = a.getDate().split("-| ");
                Date d = new Date();
                String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
                int d1,d2;
                String[] date=modifiedDate.split("-");
                d1=Integer.parseInt(app_date[2]);
                d2=Integer.parseInt(date[2]);
                if(app_date[1].equals(date[1])&&((d1-d2>=0))) {
                    aux.add(a);
                }
            }
        }
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
    public  static ObservableList<Appointment> returnMyAppointments()
    {
        ObservableList<Appointment> aux=FXCollections.observableArrayList();
        for(Appointment a:appointments)
        {
            if (a.getClient().equals(clientusr) && a.getStatus().equals(true))
                aux.add(a);
        }
        return  aux;
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
    public static void setStatustoFalse(Appointment a,File afile){
        for(Appointment aux:appointments)
        {
            if(aux.equals(a)){
                aux.setStatus(false);
            }
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(afile,appointments);
        }catch (IOException e){e.printStackTrace();}
    }
    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String a) {
        usr = a;
    }
    public static String getClientusr() {
        return clientusr;
    }

    public static void setClientusr(String a) {
        clientusr = a;
    }
    public static void removeCancelled(Appointment a,File afile)  {
        // ObservableList<Appointment> appointments;
        appointments=returnAppointments();
        appointments.removeIf(app->app.equals(a));
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //File file = new File("src\\main\\resources\\appointments.json");
            objectMapper.writeValue(afile,appointments);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void addAppointment(ArrayList<Appointment> appointms,File afile) throws EqualHour, IOException,NullPointerException {
        appointments=returnAppointments();
        //CustomerCartController c=git stanew CustomerCartController();
        //ObservableList<Appointment> ap=FXCollections.observableArrayList();
        //ap=c.handleFinishButton();
        appointments.addAll(appointms);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //File file = new File("src\\main\\resources\\appointments.json");
            objectMapper.writeValue(afile,appointments);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}