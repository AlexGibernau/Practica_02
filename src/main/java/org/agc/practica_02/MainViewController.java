package org.agc.practica_02;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.Normalizer;

public class MainViewController {
    @FXML
    private Label dniText;

    @FXML
    private ComponentController componentController;

    private String letters = "abcdefghijklmnopqrstuvwxyz";
    private String checkSum = "TRWAGMYFPDXBNJZSQVHLCKE";

    @FXML
    protected void generateDni() {
        if (componentController.getName().matches(".*\\d.*")||componentController.getSurname().matches(".*\\d.*")) {
            dniText.setText("Error: El nombre o apellido no puede contener números");
            return;
        }
        String fusion = normalize(componentController.getName().toLowerCase()) + normalize(componentController.getSurname().toLowerCase());
        if (fusion.length() < 7) {
            dniText.setText("Debe contener al menos 7 caracteres entre nombre y apellido");
            return;
        }
        String partial = fusion.substring(0, 7);
        int num=0;
        for (int i = 0; i <partial.length(); i++) {
            char letter = partial.charAt(i);
            int index = letters.indexOf(letter)%10;
            num=num*10+index;
        }
        num*=componentController.getMultiply();
        char a =checkSum.charAt(num%23);
        String formattedNum = String.format("%08d", num);
        dniText.setText(formattedNum+a);
    }
    @FXML
    protected void eraseLabel() {
        dniText.setText("");
    }
    @FXML
    protected void eraseAll(){
        componentController.setName("");
        componentController.setSurname("");
        componentController.setMultiply("");
        dniText.setText("");

    }
    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}