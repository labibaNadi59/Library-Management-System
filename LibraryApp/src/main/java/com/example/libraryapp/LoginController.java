package com.example.libraryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private void onLibrarianLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("librarian_dashboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Librarian Dashboard");
        stage.show();
    }

    @FXML
    private void onMemberLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/libraryapp/member_dashboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Member Dashboard");
        stage.show();
    }

}
