package com.example.libraryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import library.Item;
import library.Library;

import java.util.ArrayList;

public class AllItemsController {

    @FXML
    private TableView<Item> itemsTable;

    @FXML
    private TableColumn<Item, String> itemIdCol;

    @FXML
    private TableColumn<Item, String> titleCol;

    @FXML
    private TableColumn<Item, String> authorCol;

    @FXML
    private TableColumn<Item, String> categoryCol;

    @FXML
    private TableColumn<Item, String> statusCol;

    @FXML
    private TableColumn<Item, String> expectedReturnDateCol;

    @FXML
    public void initialize() {
        itemIdCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItemId()));
        titleCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitle()));
        authorCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getAuthors())));
        categoryCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategory()));
        statusCol.setCellValueFactory(cellData -> {
            String status = cellData.getValue().getIsCheckedOut() ? "Checked Out" : "Available";
            return new SimpleStringProperty(status);
        });
        expectedReturnDateCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().getExpectedReturnDate() == null) {
                return new SimpleStringProperty("N/A");
            } else {
                return new SimpleStringProperty(cellData.getValue().getExpectedReturnDate().toString());
            }
        });

        loadItems();
    }
    private void loadItems() {
        Library library = LibraryData.getLibrary();
        ArrayList<Item> itemList = library.getAllItems();
        ObservableList<Item> data = FXCollections.observableArrayList(itemList);
        itemsTable.setItems(data);
    }
}
