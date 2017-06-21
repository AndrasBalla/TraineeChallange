package main.java;

import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import main.java.Model.DBManager;
import main.java.Model.Library;
import main.java.Model.LibraryDatabase;
import main.java.View.InputValidation;
import main.java.View.Table;

public class Controller {
    public TextField inputName;
    public ChoiceBox inputType;
    public TextField inputLanguage;
    public ChoiceBox inputIntrusive;
    public ChoiceBox inputOpenSource;
    public TableView<Library> tableView;
    @FXML
    public Label message;

    @FXML
    private HBox hBox;
    @FXML
    private VBox vBox;

    private Table table = new Table();
    private LibraryDatabase db = new LibraryDatabase();
    private DBManager dbManager = new DBManager();
    private InputValidation validate;

    @FXML
    protected void initialize(){
        table.init(vBox,tableView);
        validate = new InputValidation();
        dbManager.loadLibraries(table);
        db.init();
    }

    public void add() {
        if (validate.validateUserInput(inputName.getText(), inputLanguage.getText())){
            message.setText("");
            dbManager.add(inputName.getText(),inputType.getValue().toString(),inputLanguage.getText(),inputIntrusive.getValue().toString(),inputOpenSource.getValue().toString());
            table.addData(getInput());
        }else {
            message.setText("Please enter both a name and a programming language before proceeding.");
        }
    }

    public void delete() {
        Library selected = tableView.getSelectionModel().getSelectedItem();
        if (validate.validateSelectedField(selected)) {
            dbManager.delete(selected.getName());
            table.removeData(selected);
            message.setText("");
        }else {
            message.setText("Please select a row.");
        }

    }

    public void update() {
        Library selected = tableView.getSelectionModel().getSelectedItem();
        if (validate.validateSelectedField(selected)) {
            Library input = getInput();
            dbManager.update(input,selected.getName());
            selected.setName(input.getName());
            selected.setType(input.getType());
            selected.setLanguage(input.getLanguage());
            selected.setIntrusive(input.getIntrusive());
            selected.setOpenSource(input.getOpenSource());
            tableView.refresh();
            message.setText("");
        }else {
            message.setText("Please select a row.");
        }
    }

    /**
     * Helper function to create a Library Object out of the user provided input.
     * @return
     */
    private Library getInput(){
        return new Library(inputName.getText(),inputType.getValue().toString(),inputLanguage.getText(),inputIntrusive.getValue().toString(),inputOpenSource.getValue().toString());
    }
}
