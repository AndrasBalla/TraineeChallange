package main.java.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.java.Model.Library;

public class Table {
    private ObservableList<Library> data =
            FXCollections.observableArrayList(
                    new Library("Spring","Framework", "Java", "No", ""),
                    new Library("Qi4j","Framework", "Java", "Yes", ""),
                    new Library("Apache Commons IO","Utility", "Java", "", "No")
            );

    public void init(VBox vBox, TableView table) {
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Library, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Library, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        TableColumn<Library, String> languageCol = new TableColumn<>("Language");
        languageCol.setCellValueFactory(
                new PropertyValueFactory<>("language")
        );

        TableColumn<Library, String> intrusiveCol = new TableColumn<>("Intrusive");
        intrusiveCol.setCellValueFactory(
                new PropertyValueFactory<>("intrusive")
        );

        TableColumn<Library, String> openSourceCol = new TableColumn<>("Open Source");
        openSourceCol.setCellValueFactory(
                new PropertyValueFactory<>("openSource")
        );

        table.setItems(data);
        table.getColumns().addAll(nameCol, typeCol, languageCol, intrusiveCol, openSourceCol);

        vBox.setPrefWidth(750);
        vBox.setPrefHeight(900);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 0, 10));
    }

    /**
     * Add a library to the array used by the TableView.
     * @param input, Library object provided by the user.
     */
    public void addData(Library input){
        if (!data.contains(input)){
            data.add(input);
        }
    }

    /**
     * Remove a library selected by the user.
     * @param input, The library that has been selected in the list.
     */
    public void removeData(Library input){
        data.remove(input);
    }
}
