package com.example.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import library.Item;
import library.Library;

import java.util.ArrayList;

public class ViewItemsByTypeController {

    @FXML
    private Button bookBtn;

    @FXML
    private Button publicationBtn;

    @FXML
    private TextArea itemsTextArea;

    @FXML
    private void onTypeButtonClicked(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String type = clicked.getText();

        Library library = LibraryData.getLibrary();
        ArrayList<Item> items = library.findItems(type);

        if (items == null || items.isEmpty()) {
            itemsTextArea.setText("No items found for type: " + type);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }

        itemsTextArea.setText(sb.toString());
    }
}
