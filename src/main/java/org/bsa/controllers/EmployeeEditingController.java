package org.bsa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bsa.model.Service;

public class EmployeeEditingController {
    @FXML
    TextField priceField;

    @FXML
    TextField nameField;

    @FXML
    private Button confirmButton;
    @FXML
    Label wrongPriceLabel;
    @FXML
    void handleConfirmButton() {
        if(!priceField.getText().isEmpty()) {
            try {
                Float.parseFloat(priceField.getText());
                Stage stage = (Stage) nameField.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                wrongPriceLabel.setText("Please enter a number!");
            }
        }
        else {
            priceField.setText("0.0");
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }
    public String getNewName(){
        if(nameField.getText().isEmpty())
            return null;
        else
            return nameField.getText();
    }
    public float getNewPrice(){return Float.parseFloat(priceField.getText());}
}
