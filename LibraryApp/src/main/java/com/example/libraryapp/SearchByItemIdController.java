package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.Library;
import library.Item;
import library.InvalidItemException;

public class SearchByItemIdController {

    @FXML
    private TextField itemIdField;

    @FXML
    private void onSearch() {
        String itemId = itemIdField.getText().trim();

        if (itemId.isEmpty()) {
            showAlert("Error", "Please enter an Item ID.");
            return;
        }

        Library library = LibraryData.getLibrary();

        try {
            Item foundItem = library.findItem(itemId);
            String info = "Item found:\n" +
                    "ID: " + foundItem.getItemId() + "\n" +
                    "Title: " + foundItem.getTitle() + "\n" +
                    "Category: " + foundItem.getCategory() + "\n" +
                    "Authors: " + String.join(", ", foundItem.getAuthors());

            showAlert("Search Result", info);

        } catch (InvalidItemException e) {
            showAlert("Not Found", "No item found with ID: " + itemId);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
