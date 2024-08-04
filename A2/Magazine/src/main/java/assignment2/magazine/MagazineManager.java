/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
     * @class MagazineManager
     * @brief class that handles the magazine to load,add,save  and some validations
     *
     * @author deepikaa
     * @version 02
     * @date 25/03/2024
     */
public class MagazineManager {

    private Map<String, Magazine> magazineMap;

    // Default constructor
    public MagazineManager() {
        magazineMap = new HashMap<>();
    }

    // Adding new magazine  and map it
    public void addMagazine(String magazineName) {
        Magazine magazine = new Magazine();
        magazineMap.put(magazineName, magazine);
    }

    // Retrieving mapped magazine 
    public Magazine getMagazine(String magazineName) {
        return magazineMap.get(magazineName);
    }

    // To compare magazine name with inside hashmap
    public boolean compareMagazineName(String magazineName) {
        return magazineMap.containsKey(magazineName);
    }

    // Return the arraylist of all magazine 
    public ArrayList<String> getAllMagazineNames() {
        return new ArrayList<>(magazineMap.keySet());
    }

    // Save magazine to .ser file
    public void saveMagazineToFile(String magazineName) {
        try {
            File file = new File(magazineName + ".ser");

            if (file.exists()) {
                // If the file exists, replace
                if (!file.delete()) {
                    showAlert("Failed to delete the existing file.");
                }
            }

            try (FileOutputStream outputFile = new FileOutputStream(file);
                    ObjectOutputStream objectOut = new ObjectOutputStream(outputFile)) {

                // Write the MagazineService object to the file
                objectOut.writeObject(magazineMap.get(magazineName));
                showAlert(magazineName + " has been saved successfully");

            }
        } catch (IOException ex) {
            showAlert("I/O Error");
        }
    }

    // Load magazine from .ser file
    public void loadMagazineFromFile(String magazineName) {
        try (FileInputStream inputFile = new FileInputStream(magazineName + ".ser");
                ObjectInputStream objectIn = new ObjectInputStream(inputFile)) {

            magazineMap.put(magazineName, (Magazine) objectIn.readObject());
            showAlert(magazineName + " has been loaded successfully");

        } catch (IOException | ClassNotFoundException ex) {
           showAlert("File not found");
        }
    }
    
      public void showAlert(String text) {
        Stage alertWindow = new Stage();
        Scene scene;

        // Settings of new window
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Info :");
        alertWindow.setResizable(true);

        // Label for custom alert message
        Label alertLabel = new Label(text);
        alertLabel.setStyle("-fx-font-size: 16px; -fx-wrap-text: true;");

        // Close window button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> alertWindow.close());

        // Arranging of node
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);
        
        // Add custom text and close button to VBox
        box.getChildren().addAll(alertLabel, closeButton);

        // Set scene
        scene = new Scene(box, 200, 120);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
      
      //validation of the magazine service input data
       public Boolean isValidEmailAddress(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

       public Boolean validateArray(ArrayList<Boolean> validateList) {
        Boolean isValidated = true;
        for (Boolean value : validateList) {
            if (!value) {
                isValidated = false;
                break;
            }
        }
        return isValidated;
    }
      
          public void validateIfEmpty(TextField textField, String errorMessage, ArrayList<Boolean> validateList) {
        if (textField.getText().trim().isEmpty()) {
            textField.clear();
            showAlert(errorMessage);
            validateList.add(false);
        } else {
            validateList.add(true);
        }
    }
}