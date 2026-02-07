package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.InvalidItemException;
import library.Library;

public class CheckInItemController {

    @FXML
    private TextField itemIdField;

    @FXML
    private void onCheckIn() {
        String itemId = itemIdField.getText().trim();

        if (itemId.isEmpty()) {
            showAlert("Error", "Please enter the Item ID.");
            return;
        }

        Library library = LibraryData.getLibrary();

        try {
            library.checkIn(itemId);
            library.saveData();
            showAlert("Success", "Item " + itemId + " checked in successfully.");
            itemIdField.clear();
        } catch (InvalidItemException e) {
            showAlert("Error", "Invalid Item ID: " + itemId);
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
