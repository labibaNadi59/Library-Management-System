package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.Library;

import java.util.ArrayList;
import java.util.Arrays;

public class AddNewPublicationController {

    @FXML private TextField titleField;
    @FXML private TextField categoryField;
    @FXML private TextField authorsField;
    @FXML private TextField publishYearField;
    @FXML private TextField journalField;

    @FXML
    private void onSubmit() {
        String title = titleField.getText().trim();
        String category = categoryField.getText().trim();
        String authorsInput = authorsField.getText().trim();
        String publishYearText = publishYearField.getText().trim();
        String journalOrConference = journalField.getText().trim();

        if (title.isEmpty() || category.isEmpty() || authorsInput.isEmpty() || publishYearText.isEmpty() || journalOrConference.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        int publishYear;
        try {
            publishYear = Integer.parseInt(publishYearText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Publish Year must be a valid number.");
            return;
        }

        ArrayList<String> authors = new ArrayList<>(Arrays.asList(authorsInput.split("\\s*,\\s*")));

        Library library = LibraryData.getLibrary();
        library.addPublication(title, category, authors, publishYear, journalOrConference);
        library.saveData();

        showAlert("Success", "Publication added successfully!");

        titleField.clear();
        categoryField.clear();
        authorsField.clear();
        publishYearField.clear();
        journalField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
