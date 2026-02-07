package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.CheckedOutException;
import library.InvalidItemException;
import library.InvalidMemberException;
import library.Library;

public class CheckoutItemController {

    @FXML
    private TextField itemIdField;

    @FXML
    private TextField memberIdField;

    @FXML
    private void onCheckout() {
        String itemId = itemIdField.getText().trim();
        String memberId = memberIdField.getText().trim();

        if (itemId.isEmpty() || memberId.isEmpty()) {
            showAlert("Error", "Please enter both Item ID and Member ID.");
            return;
        }

        Library library = LibraryData.getLibrary();

        try {
            library.checkOut(itemId, memberId);
            library.saveData();
            showAlert("Success", "Item " + itemId + " checked out by Member " + memberId);
            itemIdField.clear();
            memberIdField.clear();
        } catch (InvalidItemException e) {
            showAlert("Error", "Invalid Item ID: " + itemId);
        } catch (InvalidMemberException e) {
            showAlert("Error", "Invalid Member ID: " + memberId);
        } catch (CheckedOutException e) {
            showAlert("Error", "Item already checked out.");
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
