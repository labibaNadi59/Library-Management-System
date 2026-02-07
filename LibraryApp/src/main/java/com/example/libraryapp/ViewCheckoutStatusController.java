package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.Item;
import library.Library;
import library.InvalidItemException;

public class ViewCheckoutStatusController {

    @FXML
    private TextField itemIdField;

    @FXML
    private void onCheckStatus() {
        String itemId = itemIdField.getText().trim();

        if (itemId.isEmpty()) {
            showAlert("Error", "Please enter an Item ID.");
            return;
        }

        Library library = LibraryData.getLibrary();

        try {
            Item item = library.findItem(itemId);

            String status = item.getIsCheckedOut() ? "Checked Out" : "Available";
            String checkedOutBy = item.getCheckedOutBy() == null ? "N/A" : item.getCheckedOutBy();
            String returnDate = item.getExpectedReturnDate() == null ? "N/A" : item.getExpectedReturnDate().toString();

            String info = "Item ID: " + item.getItemId() + "\n" +
                    "Title: " + item.getTitle() + "\n" +
                    "Status: " + status + "\n" +
                    "Checked Out By: " + checkedOutBy + "\n" +
                    "Expected Return Date: " + returnDate;

            showAlert("Checkout Status", info);

        } catch (InvalidItemException e) {
            showAlert("Error", "No item found with ID: " + itemId);
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
