package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.Library;
import library.Item;

import java.util.ArrayList;

public class SearchByTitleAuthorController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private void onSearch() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (title.isEmpty() || author.isEmpty()) {
            showAlert("Error", "Please enter both Title and Author.");
            return;
        }

        Library library = LibraryData.getLibrary();
        ArrayList<Item> results = library.findItems(title, author);

        if (results == null || results.isEmpty()) {
            showAlert("No Results", "No items found matching the Title and Author.");
        } else {
            StringBuilder info = new StringBuilder("Items Found:\n");
            for (Item item : results) {
                info.append(item.getItemId())
                        .append(" - ")
                        .append(item.getTitle())
                        .append(" - ")
                        .append(String.join(", ", item.getAuthors()))
                        .append("\n");
            }
            showAlert("Search Result", info.toString());
        }


        titleField.clear();
        authorField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
