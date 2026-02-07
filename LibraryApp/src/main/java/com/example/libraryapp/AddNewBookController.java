package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.Library;

import java.util.Arrays;
import java.util.ArrayList;

public class AddNewBookController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField authorsField;

    @FXML
    private TextField publishYearField;

    @FXML
    private TextField publisherField;

    @FXML
    private void onSubmit() {
        String title = titleField.getText().trim();
        String category = categoryField.getText().trim();
        String authorsInput = authorsField.getText().trim();
        String publishYearText = publishYearField.getText().trim();
        String publisher = publisherField.getText().trim();

        if (title.isEmpty() || category.isEmpty() || authorsInput.isEmpty()
                || publishYearText.isEmpty() || publisher.isEmpty()) {
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
        library.addBook(title, category, authors, publishYear, publisher);
        library.saveData();

        showAlert("Success", "Book added successfully!");

        // Clear fields
        titleField.clear();
        categoryField.clear();
        authorsField.clear();
        publishYearField.clear();
        publisherField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
