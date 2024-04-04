package org.agc.practica_02;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class ComponentController {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    public TextField multiply;

    @FXML
    public Button random;

    public ComponentController() {
    }

    @FXML
    public void initialize() {
        multiply.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 1,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("[1-9]?")) {
                        return change;
                    }
                    return null;
                }));
    }

    public String getName() {
        return name.getText();
    }

    public void setName(String info) {
        name.setText(info);
    }

    public String getSurname() {
        return surname.getText();
    }

    public void setSurname(String info) {
        surname.setText(info);
    }

    public int getMultiply() {
        return Integer.parseInt(multiply.getText());
    }

    public void setMultiply(String info) {
        multiply.setText(info);
    }

    @FXML
    protected void setRandomNumber(){
        int randomNumber = (int) (Math.random()*9+1);
        multiply.setText(String.valueOf(randomNumber));
    }
}
