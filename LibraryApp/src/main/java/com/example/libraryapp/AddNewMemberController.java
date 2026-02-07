package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import library.Library;
import library.InvalidMemberException;

public class AddNewMemberController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField memberIdField;

    @FXML
    private void onSubmit() {
        String name = nameField.getText().trim();
        String memberId = memberIdField.getText().trim();

        if (name.isEmpty() || memberId.isEmpty()) {
            showAlert("Error", "Please enter both Name and Member ID.");
            return;
        }

        Library library = LibraryData.getLibrary();

        try {
            // Check korchi member already exist kore ki na
            if (library.findMember(memberId)) {
                showAlert("Duplicate Member", "Member already exists with ID: " + memberId);
                return;
            }
            // if not exist , member add korchi
            library.addMember(memberId);
            library.saveData();

            showAlert("Success", "Member Added:\nName: " + name + "\nMember ID: " + memberId);

            nameField.clear();
            memberIdField.clear();

        } catch (InvalidMemberException e) {
            showAlert("Error", e.getMessage());
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
