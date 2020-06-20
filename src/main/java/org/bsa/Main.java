package org.bsa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.bsa.model.User;
import org.bsa.service.*;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    File usrfile = new File("src\\test\\resources\\users.json");
    @Override
    public void start(Stage primaryStage) throws Exception{
        copyData();

        UserService.loadUsersFromFile(usrfile);
        //AppointmentService.writeAppointment();
        /*EmployeeService.writeEmployees();
        ServicesService.addServices();*/
        Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        Scene scene = new Scene(root, 600, 380);

        primaryStage.setTitle("Beauty Salon Application!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public void copyData() throws IOException {
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("users.json"), new File("src/main/resources/users.json"));
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("employees.json"), new File("src/main/resources/employees.json"));
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("services.json"), new File("src/main/resources/services.json"));
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("background.png"), new File("src/main/resources/background.png"));
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("Loginimage.png"), new File("src/main/resources/Loginimage.png"));
        FileUtils.copyURLToFile(Main.class.getClassLoader().getResource("appointments.json"), new File("src/main/resources/appointments.json"));
    }
    public static void main(String[] args) {
        launch(args);
    }
}
