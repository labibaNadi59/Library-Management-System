package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MemberDashboardController {

    @FXML
    private void onSearchByItemId() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_by_item_id.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Search by Item ID");
        stage.show();
    }

    @FXML
    private void onSearchByTitleAuthor() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_by_title_author.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Search by Title and Author");
        stage.show();
    }

    @FXML
    private void onCheckOutItem() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("checkout_item.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Checkout Item");
        stage.show();
    }

    @FXML
    private void onViewCheckoutStatus() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view_checkout_status.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("View Checkout Status");
        stage.show();
    }


    @FXML
    private void onViewItemsByType() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view_items_by_type.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("View Items by Type");
        stage.show();
    }

    @FXML
    private void onViewAllItems() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("all_items.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("All Library Items");
        stage.show();
    }

    @FXML
    private void onBackToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Library Management System");
        stage.show();
    }

    @FXML
    private void onExit() {
        System.exit(0);
    }
}
